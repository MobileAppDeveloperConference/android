package com.conf.mad.todo.home.model

import com.conf.mad.todo.task.model.Task

data class TaskUiModel(
    val id: Long?,
    val title: String,
    val description: String,
    val status: TaskStatus,
    val isFavorite: Boolean
) {
    val isCompleted
        get() = status == TaskStatus.COMPLETED

    private constructor(task: Task) : this(
        id = task.id,
        title = task.title,
        description = task.description,
        status = if (task.isCompleted) TaskStatus.COMPLETED else TaskStatus.TODO,
        isFavorite = task.isFavorite
    )

    fun asDomain() = Task(
        id = id,
        title = title,
        description = description,
        isFavorite = isFavorite,
        isCompleted = isCompleted
    )

    companion object {
        fun of(task: Task) = TaskUiModel(task)
    }
}
