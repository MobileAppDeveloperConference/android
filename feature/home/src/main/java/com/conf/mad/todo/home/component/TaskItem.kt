package com.conf.mad.todo.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.ComponentPreview
import com.conf.mad.todo.home.R
import com.conf.mad.todo.home.model.TaskStatus
import com.conf.mad.todo.ui.noRippleClickable

@Composable
internal fun TaskItem(
    modifier: Modifier = Modifier,
    title: String,
    status: TaskStatus,
    isFavorite: Boolean,
    onCompletedValueChange: () -> Unit,
    onFavoriteValueChange: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .background(color = TodoTheme.colors.surface),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .noRippleClickable(onClick = onCompletedValueChange),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    id = when (status) {
                        TaskStatus.TODO -> R.drawable.ic_task_default
                        TaskStatus.DONE -> R.drawable.ic_task_done
                        TaskStatus.COMPLETED -> R.drawable.ic_task_completed
                    }
                ),
                contentDescription = "Task Completed Button",
                modifier = Modifier
                    .padding(9.dp)
                    .noRippleClickable(onClick = onCompletedValueChange)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = TodoTheme.typography.regular1,
                color = if (status == TaskStatus.COMPLETED) {
                    TodoTheme.colors.onSurface30
                } else {
                    TodoTheme.colors.onSurface60
                }
            )
        }

        if (status != TaskStatus.COMPLETED) {
            Image(
                painter = painterResource(
                    id = if (isFavorite) {
                        R.drawable.ic_star_home_filled
                    } else {
                        R.drawable.ic_star_home_default
                    }
                ),
                contentDescription = "Task Favorite Button",
                modifier = Modifier
                    .padding(12.dp)
                    .noRippleClickable(onClick = onFavoriteValueChange)
            )
        }
    }
}

@ComponentPreview
@Composable
private fun TaskItemPreview() {
    TodoTheme {
        TaskItem(
            modifier = Modifier.fillMaxWidth(),
            title = "Task Title",
            status = TaskStatus.DONE,
            isFavorite = false,
            onCompletedValueChange = {},
            onFavoriteValueChange = {}
        )
    }
}
