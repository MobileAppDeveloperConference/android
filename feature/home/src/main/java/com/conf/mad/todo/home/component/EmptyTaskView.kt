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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.conf.mad.todo.designsystem.AshBlue
import com.conf.mad.todo.designsystem.SkyBlue
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.ComponentPreview
import com.conf.mad.todo.home.R

@Composable
internal fun EmptyTaskView(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.ic_clip),
            contentDescription = "Empty View",
            modifier = Modifier.padding(vertical = 18.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "투두를 추가해보세요",
            style = TodoTheme.typography.medium2,
            color = AshBlue
        )
    }
}

@ComponentPreview
@Composable
private fun EmptyTaskPreview() {
    TodoTheme {
        EmptyTaskView(
            modifier = Modifier
                .background(SkyBlue.copy(alpha = 0.3f))
                .fillMaxWidth()
                .padding(top = 20.dp)
                .height(128.dp)
        )
    }
}
