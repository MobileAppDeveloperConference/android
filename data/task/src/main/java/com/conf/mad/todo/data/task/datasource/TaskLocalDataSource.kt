package com.conf.mad.todo.data.task.datasource

import com.conf.mad.todo.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskLocalDataSource {
    fun getTodoTasks(): Flow<List<Task>>
    fun getCompletedTasks(): Flow<List<Task>>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateFavorite(id: Long, isFavorite: Boolean)
    suspend fun updateCompleted(id: Long, isCompleted: Boolean)
}
