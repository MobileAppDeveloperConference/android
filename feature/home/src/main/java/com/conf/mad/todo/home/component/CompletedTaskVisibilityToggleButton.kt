package com.conf.mad.todo.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.ComponentPreview
import com.conf.mad.todo.ui.noRippleClickable

@Composable
internal fun CompletedTaskVisibilityToggleButton(
    isVisible: Boolean,
    onToggle: () -> Unit
) {
    Text(
        text = if (isVisible) {
            "완료 숨기기"
        } else {
            "완료 보기"
        },
        style = TodoTheme.typography.medium2,
        color = TodoTheme.colors.onSurfaceContainerHigh,
        modifier = Modifier
            .background(
                color = if (isVisible) TodoTheme.colors.surfaceContainerHigh else TodoTheme.colors.primary,
                shape = RoundedCornerShape(4.dp)
            )
            .noRippleClickable(onClick = onToggle)
            .width(77.dp)
            .padding(vertical = 8.dp),
        textAlign = TextAlign.Center
    )
}

@ComponentPreview
@Composable
private fun CompletedTaskVisibilityToggleButtonPreview() {
    var isVisible by remember {
        mutableStateOf(true)
    }
    TodoTheme {
        CompletedTaskVisibilityToggleButton(
            isVisible = isVisible,
            onToggle = { isVisible = !isVisible }
        )
    }
}
