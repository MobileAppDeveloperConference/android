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
internal fun EmptyTaskView(
    modifier: Modifier = Modifier,
) {
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
