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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
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
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = CommonDrawable.ic_add_task),
                    contentDescription = "Add Task Title"
                )
                Image(
                    imageVector = ImageVector.vectorResource(
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
