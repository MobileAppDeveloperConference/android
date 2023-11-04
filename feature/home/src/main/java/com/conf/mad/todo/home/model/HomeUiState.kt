package com.conf.mad.todo.home.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeUiState(
    val isCompletedTaskVisible: Boolean = true,
    val currentDestination: HomeMenu = HomeMenu.TASK,
    val taskToDelete: TaskUiModel? = null,
    val todoTasks: ImmutableList<TaskUiModel> = persistentListOf(),
    val completedTasks: ImmutableList<TaskUiModel> = persistentListOf()
) {
    val isOnlyFavoriteTaskVisible
        get() = currentDestination == HomeMenu.FAVORITE
}
