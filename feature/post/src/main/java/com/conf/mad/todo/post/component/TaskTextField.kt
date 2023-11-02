package com.conf.mad.todo.post.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.ComponentPreview
import com.conf.mad.todo.ui.noRippleClickable

@Composable
internal fun TaskTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = false,
    useClearText: Boolean = false,
    onPressClearText: () -> Unit = {},
    placeHolder: String,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = singleLine,
        textStyle = TodoTheme.typography.regular1.copy(TodoTheme.colors.onSurface60),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(TodoTheme.colors.surfaceContainer),
                horizontalArrangement = if (useClearText) {
                    Arrangement.SpaceBetween
                } else {
                    Arrangement.Start
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    if (value.isBlank()) {
                        Text(
                            text = placeHolder,
                            style = TodoTheme.typography.regular1,
                            color = TodoTheme.colors.onSurface40,
                        )
                    }
                    Row(
                        modifier = Modifier.then(
                            if (value.isBlank()) {
                                Modifier.width(0.dp)
                            } else {
                                Modifier.weight(1f)
                            }
                        )
                    ) {
                        innerTextField()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    if (useClearText && value.isNotBlank()) {
                        Image(
                            painter = painterResource(id = CommonDrawable.ic_clear),
                            contentDescription = "Clear Text",
                            modifier = Modifier.noRippleClickable(onClick = onPressClearText)
                        )
                    }
                }
            }
        }
    )
}

@ComponentPreview
@Composable
private fun SingleLineTaskTextFieldPreview() {
    var text by remember {
        mutableStateOf("")
    }
    TodoTheme {
        TaskTextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            useClearText = true,
            placeHolder = "투두를 입력해주세요",
            onPressClearText = { text = "" }
        )
    }
}

@ComponentPreview
@Composable
private fun TaskTextFieldPreview() {
    var text by remember {
        mutableStateOf("")
    }
    TodoTheme {
        TaskTextField(
            value = text,
            onValueChange = { text = it },
            placeHolder = "원한다면 투두에 설명도 추가할 수 있어요.",
            modifier = Modifier.height(336.dp)
        )
    }
}
