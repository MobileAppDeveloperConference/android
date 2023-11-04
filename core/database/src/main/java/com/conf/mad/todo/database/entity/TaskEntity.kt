package com.conf.mad.todo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey
    val id: Long? = null,
    val title: String,
    val description: String = "",
    @ColumnInfo("is_completed") val isCompleted: Boolean = false,
    @ColumnInfo("is_favorite") val isFavorite: Boolean = false,
)
