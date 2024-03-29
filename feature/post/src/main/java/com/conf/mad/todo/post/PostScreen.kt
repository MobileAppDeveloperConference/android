/*
 * MIT License
 * Copyright 2023-2024 MADConference
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
package com.conf.mad.todo.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.DevicePreview
import com.conf.mad.todo.post.component.AddTaskTopAppBar
import com.conf.mad.todo.post.component.TaskTextField
import com.conf.mad.todo.task.model.Task
import com.conf.mad.todo.ui.noRippleClickable

const val POST_SCREEN_ROUTE = "post"
const val POST_SCREEN_TASK_ID_ARGS = "id"
const val POST_SCREEN_NAVIGATION_URI = "$POST_SCREEN_ROUTE/{$POST_SCREEN_TASK_ID_ARGS}"
const val POST_SCREEN_TASK_DEFAULT_ID = Task.UNDEFINED_ID

fun NavGraphBuilder.postScreen(onCancel: () -> Unit, onComplete: () -> Unit) {
    composable(
        POST_SCREEN_NAVIGATION_URI,
        arguments = listOf(
            navArgument(POST_SCREEN_TASK_ID_ARGS) { type = NavType.LongType }
        )
    ) {
        PostScreen(onCancel = onCancel, onComplete = onComplete)
    }
}

@Composable
fun PostScreen(onCancel: () -> Unit, onComplete: () -> Unit, viewModel: PostViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    PostScreen(
        title = uiState.title,
        description = uiState.description,
        isFavorite = uiState.isFavorite,
        onFavoritePressed = viewModel::onFavoriteChanged,
        onCancel = onCancel,
        onComplete = viewModel::onCreateNewTask,
        onTitleChanged = viewModel::onTitleChanged,
        onDescriptionChanged = viewModel::onDescriptionChanged,
        onKeyboardHide = { focusManager.clearFocus() }
    )
    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) {
            onComplete()
        }
    }
}

@Composable
fun PostScreen(
    title: String,
    description: String,
    isFavorite: Boolean,
    onFavoritePressed: () -> Unit,
    onCancel: () -> Unit,
    onComplete: () -> Unit,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onKeyboardHide: () -> Unit = {}
) {
    val isPostEnabled = remember(title) {
        title.isNotBlank()
    }
    val onCancelPressed = remember(onKeyboardHide, onCancel) {
        {
            onKeyboardHide()
            onCancel()
        }
    }
    val onPostComplete = remember(onKeyboardHide, onComplete) {
        {
            onKeyboardHide()
            onComplete()
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(TodoTheme.colors.surface)
            .noRippleClickable(onKeyboardHide),
        topBar = {
            AddTaskTopAppBar(
                isFavorite = isFavorite,
                isPostEnabled = isPostEnabled,
                onPressFavorite = onFavoritePressed,
                onCancel = onCancelPressed,
                onComplete = onPostComplete
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
                onValueChange = onTitleChanged,
                placeHolder = "투두를 입력해주세요.",
                singleLine = true,
                useClearText = true,
                onPressClearText = { onTitleChanged("") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TaskTextField(
                value = description,
                onValueChange = onDescriptionChanged,
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
        PostScreen(
            title = "",
            description = "",
            isFavorite = false,
            onFavoritePressed = {},
            onCancel = {},
            onComplete = {},
            onTitleChanged = {},
            onDescriptionChanged = {}
        )
    }
}
