package com.conf.mad.todo.data.task.mapper

import com.conf.mad.todo.database.entity.TaskEntity
import com.conf.mad.todo.task.model.Task

fun TaskEntity.asDomain(): Task = Task(
    id = id ?: Task.UNDEFINED_ID,
    title = title,
    description = description,
    isCompleted = isCompleted,
    isFavorite = isFavorite
)

fun List<TaskEntity>.asDomain(): List<Task> = map { it.asDomain() }

fun Task.toEntity(): TaskEntity = TaskEntity(
    id = id,
    title = title,
    isCompleted = isCompleted,
    isFavorite = isFavorite
)
