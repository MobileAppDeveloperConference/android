package com.conf.mad.todo.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.ComponentPreview

internal typealias CommonDrawable = com.conf.mad.todo.designsystem.R.drawable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeTopAppBar(
    isCompletedTaskVisible: Boolean,
    onToggleCompletedTaskVisibility: () -> Unit
) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = CommonDrawable.ic_title_home),
                contentDescription = "Home Title: Star"
            )
        },
        actions = {
            CompletedTaskVisibilityToggleButton(
                isVisible = isCompletedTaskVisible,
                onToggle = onToggleCompletedTaskVisibility
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    )
}

@ComponentPreview
@Composable
private fun HomeTopAppBarPreview() {
    var visibility by remember {
        mutableStateOf(true)
    }
    TodoTheme {
        HomeTopAppBar(
            isCompletedTaskVisible = visibility,
            onToggleCompletedTaskVisibility = { visibility = !visibility }
        )
    }
}
