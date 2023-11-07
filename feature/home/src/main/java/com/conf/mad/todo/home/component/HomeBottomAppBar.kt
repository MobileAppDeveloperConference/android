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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.ComponentPreview
import com.conf.mad.todo.home.R
import com.conf.mad.todo.home.model.HomeMenu

@Composable
internal fun HomeBottomAppBar(currentDestination: HomeMenu, onMenuSelected: (HomeMenu) -> Unit) {
    BottomAppBar(
        actions = {
            Image(
                painter = painterResource(
                    id = if (currentDestination == HomeMenu.TASK) {
                        R.drawable.ic_navi_task_selected
                    } else {
                        R.drawable.ic_navi_task_default
                    }
                ),
                contentDescription = "Task List Button",
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .clickable { onMenuSelected(HomeMenu.TASK) }
                    .padding(12.dp)
            )
            Image(
                painter = painterResource(
                    id = if (currentDestination == HomeMenu.FAVORITE) {
                        R.drawable.ic_navi_favorite_selected
                    } else {
                        R.drawable.ic_navi_favorite_default
                    }
                ),
                contentDescription = "Task List Button",
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .clickable { onMenuSelected(HomeMenu.FAVORITE) }
                    .padding(12.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onMenuSelected(HomeMenu.POST) },
                containerColor = TodoTheme.colors.onPrimary,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_navi_add),
                    contentDescription = "Task Post Button",
                    colorFilter = ColorFilter.tint(TodoTheme.colors.primary)
                )
            }
        },
        containerColor = TodoTheme.colors.surface
    )
}

@ComponentPreview
@Composable
private fun HomeBottomAppBarPreview() {
    val (currentDestination, onNavigate) = remember {
        mutableStateOf(HomeMenu.TASK)
    }
    TodoTheme {
        HomeBottomAppBar(
            currentDestination = currentDestination,
            onMenuSelected = onNavigate
        )
    }
}
