package com.conf.mad.todo.task.model

data class Task(
    val id: Long? = null,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val isFavorite: Boolean
) {
    companion object {
        const val UNDEFINED_ID = -1L
    }
}
