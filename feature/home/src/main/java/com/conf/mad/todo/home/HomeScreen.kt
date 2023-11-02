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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.DevicePreview
import com.conf.mad.todo.home.component.HomeBottomAppBar
import com.conf.mad.todo.home.component.HomeTopAppBar
import com.conf.mad.todo.home.component.TaskItem
import com.conf.mad.todo.home.model.HomeMenu
import com.conf.mad.todo.home.model.TaskStatus

@Composable
fun HomeScreen(
    todos: List<String> = listOf(),
    completedTasks: List<String> = listOf(),
) {
    val (isCompletedTaskVisible, onToggleCompletedTaskVisibility) = remember {
        mutableStateOf(true)
    }
    val (currentDestination, onMenuSelected) = remember {
        mutableStateOf(HomeMenu.TASK)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(TodoTheme.colors.background),
        topBar = {
            HomeTopAppBar(
                isCompletedTaskVisible = isCompletedTaskVisible,
                onToggleCompletedTaskVisibility = { onToggleCompletedTaskVisibility(!isCompletedTaskVisible) },
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
                    // TODO navigate to post screen
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
            items(todos) { todo ->
                TaskItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = todo,
                    status = TaskStatus.TODO,
                    isFavorite = false,
                    onCompletedValueChange = { },
                    onFavoriteValueChange = { },
                )
            }
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
                    title = task,
                    status = TaskStatus.COMPLETED,
                    isFavorite = false,
                    onCompletedValueChange = { },
                    onFavoriteValueChange = { },
                )
            }
        }
    }
}

@DevicePreview
@Composable
private fun HomeScreenPreview() {
    val todos = listOf("투두투두투", "두투두투두투", "투두투두투두")
    val completedTasks = listOf("완료투두투두투", "완료두투두투두투", "완료투두투두투두투")
    TodoTheme {
        HomeScreen(
            todos = todos,
            completedTasks = completedTasks
        )
    }
}
