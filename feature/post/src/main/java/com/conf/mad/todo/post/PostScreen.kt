package com.conf.mad.todo.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.DevicePreview
import com.conf.mad.todo.post.component.AddTaskTopAppBar
import com.conf.mad.todo.post.component.TaskTextField

const val POST_SCREEN_ROUTE = "post"

fun NavGraphBuilder.postScreen(
    onCancel: () -> Unit,
    onComplete: () -> Unit
) {
    composable(POST_SCREEN_ROUTE) {
        PostScreen(onCancel = onCancel, onComplete = onComplete)
    }
}

@Composable
fun PostScreen(
    onCancel: () -> Unit = {},
    onComplete: () -> Unit = {},
) {
    var isFavorite by remember {
        mutableStateOf(false)
    }
    var title by remember {
        mutableStateOf("")
    }
    val isPostEnabled by remember(title) {
        derivedStateOf {
            title.isNotEmpty()
        }
    }
    var content by remember {
        mutableStateOf("")
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(TodoTheme.colors.surface),
        topBar = {
            AddTaskTopAppBar(
                isFavorite = isFavorite,
                isPostEnabled = isPostEnabled,
                onPressFavorite = { isFavorite = !isFavorite },
                onCancel = onCancel,
                onComplete = onComplete
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(
                bottom = paddingValues.calculateBottomPadding(),
                top = paddingValues.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp
            )
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            TaskTextField(
                value = title,
                onValueChange = { title = it },
                placeHolder = "투두를 입력해주세요.",
                singleLine = true,
                useClearText = true,
                onPressClearText = { title = "" },
            )
            Spacer(modifier = Modifier.height(16.dp))
            TaskTextField(
                value = content,
                onValueChange = { content = it },
                placeHolder = "원한다면 투두에 설명도 추가할 수 있어요.",
                modifier = Modifier.height(336.dp)
            )
        }
    }
}

@DevicePreview
@Composable
private fun PostScreenPreview() {
    TodoTheme {
        PostScreen()
    }
}
