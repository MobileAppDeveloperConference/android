package com.conf.mad.todo.convention

import com.conf.mad.todo.dsl.implementation
import com.conf.mad.todo.dsl.library
import com.conf.mad.todo.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PureKotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("java-library")
            apply("org.jetbrains.kotlin.jvm")
        }
        dependencies {
            implementation(libs.library("javax-inject"))
            implementation(libs.library("kotlinx-coroutines-core"))
        }
    }
}
