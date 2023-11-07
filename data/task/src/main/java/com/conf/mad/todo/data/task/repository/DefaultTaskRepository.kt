/*
 * MIT License
 * Copyright 2023 MADConference
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.conf.mad.todo.data.task.repository

import com.conf.mad.todo.common.Dispatcher
import com.conf.mad.todo.common.DispatcherType.DEFAULT
import com.conf.mad.todo.data.task.mapper.asDomain
import com.conf.mad.todo.data.task.mapper.toEntity
import com.conf.mad.todo.database.TaskDao
import com.conf.mad.todo.task.model.Task
import com.conf.mad.todo.task.repository.TaskRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

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
