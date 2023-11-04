package com.conf.mad.todo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    @ColumnInfo("is_completed") val isCompleted: Boolean = false,
    @ColumnInfo("is_favorite") val isFavorite: Boolean = false,
)
