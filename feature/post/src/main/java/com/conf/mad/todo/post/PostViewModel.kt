package com.conf.mad.todo.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conf.mad.todo.post.model.PostUiState
import com.conf.mad.todo.task.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PostUiState())
    val uiState = _uiState.asStateFlow()

    fun onTitleChanged(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun onDescriptionChanged(description: String) {
        _uiState.update {
            it.copy(description = description)
        }
    }

    fun onFavoriteChanged() {
        _uiState.update {
            it.copy(isFavorite = !it.isFavorite)
        }
    }

    fun onCreateNewTask() = viewModelScope.launch {
        repository.insertTask(uiState.value.asDomain())
        _uiState.update {
            it.copy(isSaved = true)
        }
    }
}
