package com.conf.mad.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.conf.mad.todo.database.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = true)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}