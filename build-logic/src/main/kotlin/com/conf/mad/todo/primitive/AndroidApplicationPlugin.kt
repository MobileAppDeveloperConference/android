package com.conf.mad.todo.primitive

import com.conf.mad.todo.dsl.androidApplication
import com.conf.mad.todo.dsl.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            androidApplication {
                setupAndroid()
            }
        }
    }
}
