package com.conf.mad.todo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.conf.mad.todo.database.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    /**
     * Get tasks by isCompleted is false
     */
    @Query("SELECT * FROM TaskEntity WHERE is_completed = 0")
    fun getTodoTasks(): Flow<List<TaskEntity>>

    /**
     * Get tasks by isCompleted is true
     */
    @Query("SELECT * FROM TaskEntity WHERE is_completed = 1")
    fun getCompletedTasks(): Flow<List<TaskEntity>>

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    /**
     * Updating only isFavorite
     * By id
     */
    @Query("UPDATE TaskEntity SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(id: Long, isFavorite: Boolean)

    /**
     * Updating only isCompleted
     * By id
     */
    @Query("UPDATE TaskEntity SET is_completed = :isCompleted WHERE id = :id")
    suspend fun updateCompleted(id: Long, isCompleted: Boolean)
}