package com.conf.mad.todo.data.task.datasource

import com.conf.mad.todo.data.task.mapper.asDomain
import com.conf.mad.todo.data.task.mapper.toEntity
import com.conf.mad.todo.database.TaskDao
import com.conf.mad.todo.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultTaskLocalDataSource @Inject constructor(
    private val taskDao: TaskDao
) : TaskLocalDataSource {
    override fun getTodoTasks(): Flow<List<Task>> {
        return taskDao.getTodoTasks().map { it.asDomain() }
    }

    override fun getCompletedTasks(): Flow<List<Task>> {
        return taskDao.getCompletedTasks().map { it.asDomain() }
    }

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.insertTask(task.toEntity())
    }

    override suspend fun updateFavorite(id: Long, isFavorite: Boolean) {
        taskDao.updateFavorite(id, isFavorite)
    }

    override suspend fun updateCompleted(id: Long, isCompleted: Boolean) {
        taskDao.updateCompleted(id, isCompleted)
    }
}
