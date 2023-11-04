package com.conf.mad.todo.data.task.datasource

import com.conf.mad.todo.model.Task

interface TaskLocalDataSource {
    fun getTodoTasks(): List<Task>
    fun getCompletedTasks(): List<Task>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateFavorite(id: Long, isFavorite: Boolean)
    suspend fun updateCompleted(id: Long, isCompleted: Boolean)
}
