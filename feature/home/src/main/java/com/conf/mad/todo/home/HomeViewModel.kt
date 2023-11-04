package com.conf.mad.todo.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conf.mad.todo.home.model.HomeMenu
import com.conf.mad.todo.home.model.HomeUiState
import com.conf.mad.todo.home.model.TaskUiModel
import com.conf.mad.todo.task.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

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
                        }.map(TaskUiModel::of).toPersistentList(),
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
        viewModelScope.launch {
            repository.updateCompleted(id, isCompleted)
        }
    }
}
