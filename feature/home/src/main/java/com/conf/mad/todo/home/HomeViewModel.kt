/*
 * MIT License
 * Copyright 2023 MADConference
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.conf.mad.todo.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conf.mad.todo.home.model.HomeMenu
import com.conf.mad.todo.home.model.HomeUiState
import com.conf.mad.todo.home.model.TaskStatus
import com.conf.mad.todo.home.model.TaskUiModel
import com.conf.mad.todo.task.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                repository.getTodoTasks(),
                repository.getCompletedTasks(),
                uiState
            ) { todoTasks, completedTasks, uiState ->
                Triple(todoTasks, completedTasks, uiState)
            }.collect { (todos, completed, uiState) ->
                _uiState.update {
                    it.copy(
                        todoTasks = todos.filter { task ->
                            if (!uiState.isOnlyFavoriteTaskVisible) {
                                return@filter true
                            }
                            task.isFavorite
                        }.map(TaskUiModel::of).map { task ->
                            if (it.doneTasks.find { doneTask -> doneTask.id == task.id } != null) {
                                task.copy(status = TaskStatus.DONE)
                            } else {
                                task
                            }
                        }.toPersistentList(),
                        completedTasks = completed.filter { task ->
                            if (!uiState.isOnlyFavoriteTaskVisible) {
                                return@filter true
                            }
                            task.isFavorite
                        }.map(TaskUiModel::of).toPersistentList()
                    )
                }
            }
        }
    }

    fun onToggleCompletedTaskVisibility() {
        _uiState.update {
            it.copy(isCompletedTaskVisible = !it.isCompletedTaskVisible)
        }
    }

    fun onTaskChanged(item: HomeMenu) {
        _uiState.update {
            it.copy(currentDestination = item)
        }
    }

    fun onFavoriteChanged(id: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.updateFavorite(id, isFavorite)
        }
    }

    fun onCompletedChanged(id: Long, isCompleted: Boolean) {
        if (isCompleted) {
            _uiState.update {
                val task = it.todoTasks.find { task -> task.id == id } ?: return@update it
                it.copy(
                    doneTasks = (it.doneTasks + task).toPersistentList()
                )
            }
        }
        viewModelScope.launch {
            if (isCompleted) {
                delay(500)
            }
            repository.updateCompleted(id, isCompleted)
            _uiState.update {
                it.copy(
                    doneTasks = it.doneTasks.filter { task -> task.id != id }.toPersistentList()
                )
            }
        }
    }

    fun onSelectTaskToDelete(task: TaskUiModel) {
        _uiState.update {
            it.copy(taskToDelete = task)
        }
    }

    fun onDeleteTask() {
        viewModelScope.launch {
            val task = uiState.value.taskToDelete ?: return@launch
            repository.deleteTask(task.asDomain())
            _uiState.update {
                it.copy(taskToDelete = null)
            }
        }
    }

    fun onDismissDeleteDialog() {
        _uiState.update {
            it.copy(taskToDelete = null)
        }
    }
}
