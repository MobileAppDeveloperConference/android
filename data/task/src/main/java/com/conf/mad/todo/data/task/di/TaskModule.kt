package com.conf.mad.todo.data.task.di

import com.conf.mad.todo.data.task.datasource.DefaultTaskLocalDataSource
import com.conf.mad.todo.data.task.datasource.TaskLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TaskModule {
    @Binds
    @Singleton
    fun bindTaskLocalDataSource(source: DefaultTaskLocalDataSource): TaskLocalDataSource
}
