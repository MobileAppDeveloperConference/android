package com.conf.mad.todo.post.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.ComponentPreview
import com.conf.mad.todo.ui.noRippleClickable

typealias CommonDrawable = com.conf.mad.todo.designsystem.R.drawable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddTaskTopAppBar(
    isFavorite: Boolean,
    isPostEnabled: Boolean,
    onPressFavorite: () -> Unit,
    onCancel: () -> Unit,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = CommonDrawable.ic_add_task),
                    contentDescription = "Add Task Title"
                )
                Image(
                    painter = painterResource(
                        id = if (isFavorite) {
                            CommonDrawable.ic_star_filled
                        } else {
                            CommonDrawable.ic_star_default
                        }
                    ),
                    contentDescription = "Add Task Title",
                    modifier = Modifier
                        .noRippleClickable(onClick = onPressFavorite)
                        .padding(8.dp)
                )
            }
        },
        actions = {
            Text(
                text = "취소",
                color = TodoTheme.colors.onSurface50,
                style = TodoTheme.typography.medium1,
                modifier = Modifier
                    .noRippleClickable(onClick = onCancel)
                    .padding(16.dp)
            )
            Text(
                text = "완료",
                color = if (isPostEnabled) {
                    TodoTheme.colors.primary
                } else {
                    TodoTheme.colors.onSurface50
                },
                style = TodoTheme.typography.medium1,
                modifier = Modifier
                    .then(
                        if (isPostEnabled) {
                            Modifier.noRippleClickable(onClick = onComplete)
                        } else {
                            Modifier
                        }
                    )
                    .padding(16.dp)
            )
        },
        modifier = modifier
    )
}

@ComponentPreview
@Composable
private fun TodoTopAppBarPreview() {
    TodoTheme {
        AddTaskTopAppBar(
            isFavorite = true,
            isPostEnabled = true,
            onPressFavorite = {},
            onCancel = {},
            onComplete = {}
        )
    }
}
