package com.conf.mad.todo.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("conf.mad.primitive.android")
                apply("conf.mad.primitive.android.kotlin")
                apply("conf.mad.primitive.android.compose")
                apply("conf.mad.primitive.android.hilt")
            }
        }
    }
}