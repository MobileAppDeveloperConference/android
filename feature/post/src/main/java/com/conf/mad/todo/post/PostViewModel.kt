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
package com.conf.mad.todo.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conf.mad.todo.post.model.PostUiState
import com.conf.mad.todo.task.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: TaskRepository
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
