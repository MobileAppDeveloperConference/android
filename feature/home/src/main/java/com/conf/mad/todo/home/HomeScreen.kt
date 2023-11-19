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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.conf.mad.todo.designsystem.SkyBlue
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.home.component.DeleteTaskDialog
import com.conf.mad.todo.home.component.EmptyTaskView
import com.conf.mad.todo.home.component.HomeBottomAppBar
import com.conf.mad.todo.home.component.HomeTopAppBar
import com.conf.mad.todo.home.component.TaskItem
import com.conf.mad.todo.home.model.HomeMenu
import com.conf.mad.todo.task.model.Task.Companion.UNDEFINED_ID

const val HOME_SCREEN_ROUTE = "home"

fun NavGraphBuilder.homeScreen(onPost: () -> Unit) {
    composable(HOME_SCREEN_ROUTE) {
        HomeScreen(onPost = onPost)
    }
}

@Composable
fun HomeScreen(onPost: () -> Unit, viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isDeleteDialogVisible = remember(uiState.taskToDelete) {
        uiState.taskToDelete != null
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(TodoTheme.colors.background),
        topBar = {
            HomeTopAppBar(
                isCompletedTaskVisible = uiState.isCompletedTaskVisible,
                onToggleCompletedTaskVisibility = viewModel::onToggleCompletedTaskVisibility
            )
        },
        bottomBar = {
            HomeBottomAppBar(
                currentDestination = uiState.currentDestination,
                onMenuSelected = {
                    if (it != HomeMenu.POST) {
                        viewModel.onTaskChanged(it)
                        return@HomeBottomAppBar
                    }
                    onPost()
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            )
        ) {
            item(key = "todo title") {
                Text(
                    text = "하는 중",
                    style = TodoTheme.typography.semiBold,
                    color = TodoTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            if (uiState.todoTasks.isEmpty()) {
                item(key = "empty todo view") {
                    EmptyTaskView(
                        modifier = Modifier
                            .background(SkyBlue.copy(alpha = 0.2f))
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .height(128.dp)
                    )
                }
            }
            items(uiState.todoTasks, key = { it.id ?: UNDEFINED_ID }) { todo ->
                TaskItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = todo.title,
                    status = todo.status,
                    isFavorite = todo.isFavorite,
                    onCompletedValueChange = {
                        viewModel.onCompletedChanged(
                            todo.id ?: UNDEFINED_ID,
                            !todo.isCompleted
                        )
                    },
                    onFavoriteValueChange = {
                        viewModel.onFavoriteChanged(
                            todo.id ?: UNDEFINED_ID,
                            !todo.isFavorite
                        )
                    },
                    onDeleteDialogShow = { viewModel.onSelectTaskToDelete(todo) }
                )
            }
            if (uiState.isCompletedTaskVisible) {
                item(key = "complete title") {
                    Spacer(modifier = Modifier.height(36.dp))
                    Text(
                        text = "완료",
                        style = TodoTheme.typography.semiBold,
                        color = TodoTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                if (uiState.completedTasks.isEmpty()) {
                    item(key = "empty completed task view") {
                        EmptyTaskView(
                            modifier = Modifier
                                .background(SkyBlue.copy(alpha = 0.2f))
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                                .height(128.dp)
                        )
                    }
                }
                items(uiState.completedTasks) { task ->
                    TaskItem(
                        modifier = Modifier.fillMaxWidth(),
                        title = task.title,
                        status = task.status,
                        isFavorite = task.isFavorite,
                        onCompletedValueChange = {
                            viewModel.onCompletedChanged(
                                task.id ?: UNDEFINED_ID,
                                !task.isCompleted
                            )
                        },
                        onFavoriteValueChange = {
                            viewModel.onFavoriteChanged(
                                task.id ?: UNDEFINED_ID,
                                !task.isFavorite
                            )
                        },
                        onDeleteDialogShow = { viewModel.onSelectTaskToDelete(task) }
                    )
                }
            }
        }
        if (isDeleteDialogVisible) {
            DeleteTaskDialog(
                onDismissRequest = viewModel::onDismissDeleteDialog,
                onConfirm = viewModel::onDeleteTask
            )
        }
    }
}
