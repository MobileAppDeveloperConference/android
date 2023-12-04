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
package com.conf.mad.todo.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TaskItem(
    title: String,
    status: TaskStatus,
    isFavorite: Boolean,
    onCompletedValueChange: () -> Unit,
    onFavoriteValueChange: () -> Unit,
    onDeleteDialogShow: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = TodoTheme.colors.surface),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .combinedClickable(
                    onClick = onCompletedValueChange,
                    onLongClick = onDeleteDialogShow
                ).weight(1f),
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
            title = "Task Title",
            status = TaskStatus.DONE,
            isFavorite = false,
            onCompletedValueChange = {},
            onFavoriteValueChange = {},
            onDeleteDialogShow = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
