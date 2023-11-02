package com.conf.mad.todo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var title: String,
    @ColumnInfo("is_completed") var isCompleted: Boolean = false,
    @ColumnInfo("is_favorite") var isFavorite: Boolean = false,
)
