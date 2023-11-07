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

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
class TodoColors(
    primary: Color,
    onPrimary: Color,
    secondary: Color,
    background: Color,
    onBackground: Color,
    surface: Color,
    onSurface60: Color,
    onSurface50: Color,
    onSurface40: Color,
    onSurface30: Color,
    onSurface20: Color,
    onSurface10: Color,
    surfaceContainer: Color,
    onSurfaceContainer: Color,
    surfaceContainerHigh: Color,
    onSurfaceContainerHigh: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary)
        private set
    var onPrimary by mutableStateOf(onPrimary)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var background by mutableStateOf(background)
        private set
    var onBackground by mutableStateOf(onBackground)
        private set
    var surface by mutableStateOf(surface)
        private set
    var onSurface60 by mutableStateOf(onSurface60)
        private set
    var onSurface50 by mutableStateOf(onSurface50)
        private set
    var onSurface40 by mutableStateOf(onSurface40)
        private set
    var onSurface30 by mutableStateOf(onSurface30)
        private set
    var onSurface20 by mutableStateOf(onSurface20)
        private set
    var onSurface10 by mutableStateOf(onSurface10)
        private set
    var surfaceContainer by mutableStateOf(surfaceContainer)
        private set
    var onSurfaceContainer by mutableStateOf(onSurfaceContainer)
        private set
    var surfaceContainerHigh by mutableStateOf(surfaceContainerHigh)
        private set
    var onSurfaceContainerHigh by mutableStateOf(onSurfaceContainerHigh)
        private set

    var isLight by mutableStateOf(isLight)

    fun copy(): TodoColors = TodoColors(
        primary,
        onPrimary,
        secondary,
        background,
        onBackground,
        surface,
        onSurface60,
        onSurface50,
        onSurface40,
        onSurface30,
        onSurface20,
        onSurface10,
        surfaceContainer,
        onSurfaceContainer,
        surfaceContainerHigh,
        onSurfaceContainerHigh,
        isLight
    )

    fun update(other: TodoColors) {
        primary = other.primary
        onPrimary = other.onPrimary
        secondary = other.secondary
        background = other.background
        onBackground = other.onBackground
        surface = other.surface
        onSurface60 = other.onSurface60
        onSurface50 = other.onSurface50
        onSurface40 = other.onSurface40
        onSurface30 = other.onSurface30
        onSurface20 = other.onSurface20
        onSurface10 = other.onSurface10
        surfaceContainer = other.surfaceContainer
        onSurfaceContainer = other.onSurfaceContainer
        surfaceContainerHigh = other.surfaceContainerHigh
        onSurfaceContainerHigh = other.onSurfaceContainerHigh
        isLight = other.isLight
    }
}

fun todoColors(
    primary: Color = Red,
    onPrimary: Color = Pink,
    secondary: Color = Blue,
    background: Color = LightBlue,
    onBackground: Color = Black,
    surface: Color = Snow,
    onSurface60: Color = Black,
    onSurface50: Color = GrayGugu,
    onSurface40: Color = GrayAbby,
    onSurface30: Color = GrayCeci,
    onSurface20: Color = GrayEunbi,
    onSurface10: Color = GrayFry,
    surfaceContainer: Color = GrayFry,
    onSurfaceContainer: Color = Black,
    surfaceContainerHigh: Color = Black,
    onSurfaceContainerHigh: Color = Snow,
    isLight: Boolean = true
): TodoColors {
    return TodoColors(
        primary,
        onPrimary,
        secondary,
        background,
        onBackground,
        surface,
        onSurface60,
        onSurface50,
        onSurface40,
        onSurface30,
        onSurface20,
        onSurface10,
        surfaceContainer,
        onSurfaceContainer,
        surfaceContainerHigh,
        onSurfaceContainerHigh,
        isLight
    )
}

private val LocalTodoColors = staticCompositionLocalOf<TodoColors> {
    error("No TodoColors provided")
}
private val LocalTodoTypography = staticCompositionLocalOf<TodoTypography> {
    error("No TodoTypography provided")
}

object TodoTheme {
    val colors: TodoColors @Composable get() = LocalTodoColors.current
    val typography: TodoTypography @Composable get() = LocalTodoTypography.current
}

@Composable
fun ProvideTodoColorAndTypography(
    colors: TodoColors,
    typography: TodoTypography,
    content: @Composable () -> Unit
) {
    val provideColors = remember { colors.copy() }
    provideColors.update(colors)
    val provideTypography = remember { typography.copy() }
    provideTypography.update(typography)
    CompositionLocalProvider(
        LocalTodoColors provides provideColors,
        LocalTodoTypography provides provideTypography,
        content = content
    )
}

@Composable
fun TodoTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colors = todoColors()
    val typography = TodoTypography()
    ProvideTodoColorAndTypography(colors, typography) {
        MaterialTheme(content = content)
    }
}
