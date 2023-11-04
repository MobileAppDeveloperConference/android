package com.conf.mad.todo.data.task.repository

import com.conf.mad.todo.common.Dispatcher
import com.conf.mad.todo.common.DispatcherType.DEFAULT
import com.conf.mad.todo.data.task.mapper.asDomain
import com.conf.mad.todo.data.task.mapper.toEntity
import com.conf.mad.todo.database.TaskDao
import com.conf.mad.todo.task.model.Task
import com.conf.mad.todo.task.repository.TaskRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultTaskRepository @Inject constructor(
    private val taskDao: TaskDao,
    @Dispatcher(DEFAULT) private val dispatcher: CoroutineDispatcher
) : TaskRepository {
    override fun getTodoTasks(): Flow<List<Task>> {
        return taskDao.getTodoTasks().map {
            withContext(dispatcher) {
                it.asDomain()
            }
        }
    }

    override fun getCompletedTasks(): Flow<List<Task>> {
        return taskDao.getCompletedTasks().map {
            withContext(dispatcher) {
                it.asDomain()
            }
        }
    }

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task.toEntity())
    }

    override suspend fun updateFavorite(id: Long, isFavorite: Boolean) {
        taskDao.updateFavorite(id, isFavorite)
    }

    override suspend fun updateCompleted(id: Long, isCompleted: Boolean) {
        taskDao.updateCompleted(id, isCompleted)
    }
}
