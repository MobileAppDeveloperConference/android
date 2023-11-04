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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.DevicePreview
import com.conf.mad.todo.home.component.HomeBottomAppBar
import com.conf.mad.todo.home.component.HomeTopAppBar
import com.conf.mad.todo.home.component.TaskItem
import com.conf.mad.todo.home.model.HomeMenu
import com.conf.mad.todo.home.model.TaskStatus
import com.conf.mad.todo.task.model.Task
import com.conf.mad.todo.task.model.Task.Companion.UNDEFINED_ID
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

const val HOME_SCREEN_ROUTE = "home"

fun NavGraphBuilder.homeScreen(
    onPost: () -> Unit
) {
    composable(HOME_SCREEN_ROUTE) {
        HomeScreen(onPost = onPost)
    }
}

@Composable
fun HomeScreen(
    onPost: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        isCompletedTaskVisible = uiState.isCompletedTaskVisible,
        currentDestination = uiState.currentDestination,
        todos = uiState.todoTasks,
        completedTasks = uiState.completedTasks,
        onToggleCompletedTaskVisibility = viewModel::onToggleCompletedTaskVisibility,
        onMenuSelected = viewModel::onTaskChanged,
        onPost = onPost,
        onFavoriteChanged = viewModel::onFavoriteChanged,
        onCompletedChanged = viewModel::onCompletedChanged,
    )
}

@Composable
fun HomeScreen(
    isCompletedTaskVisible: Boolean,
    currentDestination: HomeMenu,
    todos: ImmutableList<Task>,
    completedTasks: ImmutableList<Task>,
    onToggleCompletedTaskVisibility: () -> Unit,
    onMenuSelected: (HomeMenu) -> Unit,
    onPost: () -> Unit,
    onFavoriteChanged: (Long, Boolean) -> Unit,
    onCompletedChanged: (Long, Boolean) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(TodoTheme.colors.background),
        topBar = {
            HomeTopAppBar(
                isCompletedTaskVisible = isCompletedTaskVisible,
                onToggleCompletedTaskVisibility = { onToggleCompletedTaskVisibility() },
            )
        },
        bottomBar = {
            HomeBottomAppBar(
                currentDestination = currentDestination,
                onMenuSelected = {
                    if (it != HomeMenu.POST) {
                        onMenuSelected(it)
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
            items(todos, key = { it.id ?: UNDEFINED_ID }) { todo ->
                TaskItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = todo.title,
                    status = TaskStatus.TODO,
                    isFavorite = todo.isFavorite,
                    onCompletedValueChange = {
                        onCompletedChanged(
                            todo.id ?: UNDEFINED_ID,
                            !todo.isCompleted
                        )
                    },
                    onFavoriteValueChange = {
                        onFavoriteChanged(
                            todo.id ?: UNDEFINED_ID,
                            !todo.isFavorite
                        )
                    },
                )
            }
            if (isCompletedTaskVisible) {
                item(key = "complete title") {
                    Spacer(modifier = Modifier.height(36.dp))
                    Text(
                        text = "완료",
                        style = TodoTheme.typography.semiBold,
                        color = TodoTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                items(completedTasks) { task ->
                    TaskItem(
                        modifier = Modifier.fillMaxWidth(),
                        title = task.title,
                        status = TaskStatus.COMPLETED,
                        isFavorite = task.isFavorite,
                        onCompletedValueChange = {
                            onCompletedChanged(
                                task.id ?: UNDEFINED_ID,
                                !task.isCompleted
                            )
                        },
                        onFavoriteValueChange = {
                            onFavoriteChanged(
                                task.id ?: UNDEFINED_ID,
                                !task.isFavorite
                            )
                        },
                    )
                }
            }
        }
    }
}

@DevicePreview
@Composable
private fun HomeScreenPreview() {
    val todos: PersistentList<Task> = persistentListOf(
        Task(
            title = "투두투두투두투",
            description = "",
            isFavorite = true,
            isCompleted = false,
            id = 0,
        ),
        Task(
            title = "투두투두투두투",
            description = "",
            isFavorite = true,
            isCompleted = false,
            id = 1,
        ),
    )
    val completedTasks: PersistentList<Task> = persistentListOf(
        Task(
            title = "투두투두투두투",
            description = "",
            isFavorite = true,
            isCompleted = false,
            id = 2,
        ),
        Task(
            title = "투두투두투두투",
            description = "",
            isFavorite = true,
            isCompleted = false,
            id = 3,
        ),
    )
    TodoTheme {
        HomeScreen(
            isCompletedTaskVisible = true,
            currentDestination = HomeMenu.POST,
            todos = todos,
            completedTasks = completedTasks,
            onToggleCompletedTaskVisibility = {},
            onMenuSelected = {},
            onPost = {},
            onFavoriteChanged = { _, _ -> },
            onCompletedChanged = { _, _ -> },
        )
    }
}
