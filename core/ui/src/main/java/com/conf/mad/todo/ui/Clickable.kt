package com.conf.mad.todo.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@Composable
inline fun Modifier.noRippleClickable(
    crossinline onClick: () -> Unit
): Modifier = then(
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
inline fun Modifier.noRippleCombinedClickable(
    noinline onClick: () -> Unit,
    noinline onLongClick: () -> Unit
): Modifier = composed {
    this.combinedClickable(
        onClick = onClick,
        onLongClick = onLongClick,
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    )
}
