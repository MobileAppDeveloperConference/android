package com.conf.mad.todo.home.model

import com.conf.mad.todo.task.model.Task
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeUiState(
    val isCompletedTaskVisible: Boolean = false,
    val currentDestination: HomeMenu = HomeMenu.TASK,
    val todoTasks: ImmutableList<Task> = persistentListOf(),
    val completedTasks: ImmutableList<Task> = persistentListOf()
)
