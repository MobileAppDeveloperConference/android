package com.conf.mad.todo.post.model

import com.conf.mad.todo.task.model.Task

data class PostUiState(
    val title: String = "",
    val description: String = "",
    val isFavorite: Boolean = false
) {
    fun asDomain() = Task(
        title = title,
        description = description,
        isFavorite = isFavorite
    )
}
