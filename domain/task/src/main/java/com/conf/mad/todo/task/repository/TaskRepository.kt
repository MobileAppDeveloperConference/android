package com.conf.mad.todo.task.repository

import com.conf.mad.todo.task.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTodoTasks(): Flow<List<Task>>
    fun getCompletedTasks(): Flow<List<Task>>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateFavorite(id: Long, isFavorite: Boolean)
    suspend fun updateCompleted(id: Long, isCompleted: Boolean)
}
