package com.conf.mad.todo.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.designsystem.preview.DevicePreview

@Composable
fun DeleteTaskDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .height(240.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = TodoTheme.colors.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "투두를 삭제할까요?",
                        style = TodoTheme.typography.medium1.copy(fontSize = 20.sp),
                        color = TodoTheme.colors.onSurface60
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "삭제하면 되돌릴 수 없어요.",
                        style = TodoTheme.typography.regular1,
                        color = TodoTheme.colors.onSurface50
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    TextButton(
                        onClick = onDismissRequest,
                    ) {
                        Text(
                            text = "취소",
                            style = TodoTheme.typography.medium1,
                            color = TodoTheme.colors.onSurface60
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(
                        onClick = onConfirm,
                    ) {
                        Text(
                            text = "삭제하기",
                            style = TodoTheme.typography.medium1,
                            color = TodoTheme.colors.primary
                        )
                    }
                }
            }
        }
    }
}

@DevicePreview
@Composable
private fun DeleteTaskDialogPreview() {
    TodoTheme {
        DeleteTaskDialog(
            onDismissRequest = {},
            onConfirm = {}
        )
    }
}
