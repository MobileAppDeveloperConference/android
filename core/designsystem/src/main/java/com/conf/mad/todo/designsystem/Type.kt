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
package com.conf.mad.todo.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val StretchPro = FontFamily(Font(R.font.stretch_pro, FontWeight.Bold))
val PretendardMedium = FontFamily(Font(R.font.pretendard_medium, FontWeight.Medium))
val PretendardRegular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal))
val PretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold))

@Stable
class TodoTypography(
    title: TextStyle,
    semiBold: TextStyle,
    medium1: TextStyle,
    medium2: TextStyle,
    regular1: TextStyle,
    regular2: TextStyle
) {
    var title: TextStyle by mutableStateOf(title)
        private set
    var semiBold: TextStyle by mutableStateOf(semiBold)
        private set
    var medium1: TextStyle by mutableStateOf(medium1)
        private set
    var medium2: TextStyle by mutableStateOf(medium2)
        private set
    var regular1: TextStyle by mutableStateOf(regular1)
        private set
    var regular2: TextStyle by mutableStateOf(regular2)
        private set

    fun update(other: TodoTypography) {
        title = other.title
        semiBold = other.semiBold
        medium1 = other.medium1
        medium2 = other.medium2
        regular1 = other.regular1
        regular2 = other.regular2
    }

    fun copy(
        title: TextStyle = this.title,
        semiBold: TextStyle = this.semiBold,
        medium1: TextStyle = this.medium1,
        medium2: TextStyle = this.medium2,
        regular1: TextStyle = this.regular1,
        regular2: TextStyle = this.regular2
    ): TodoTypography = TodoTypography(title, semiBold, medium1, medium2, regular1, regular2)
}

@Composable
fun TodoTypography(): TodoTypography {
    return TodoTypography(
        title = TextStyle(
            fontFamily = StretchPro,
            fontSize = 24.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.5).sp
        ),
        semiBold = TextStyle(
            fontFamily = PretendardSemiBold,
            fontSize = 16.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = (-0.5).sp
        ),
        medium1 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.5).sp
        ),
        medium2 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 13.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.5).sp
        ),
        regular1 = TextStyle(
            fontFamily = PretendardRegular,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = (-0.5).sp
        ),
        regular2 = TextStyle(
            fontFamily = PretendardRegular,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Normal
        )
    )
}
