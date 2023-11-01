package com.conf.mad.todo.primitive

import com.conf.mad.todo.dsl.android
import com.conf.mad.todo.dsl.implementation
import com.conf.mad.todo.dsl.ksp
import com.conf.mad.todo.dsl.library
import com.conf.mad.todo.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }
            android {
                defaultConfig {
                    javaCompileOptions {
                        annotationProcessorOptions {
                            arguments += mapOf(
                                "room.schemaLocation" to "$projectDir/schemas",
                                "room.incremental" to "true"
                            )
                        }
                    }
                }
                dependencies {
                    implementation(libs.library("androidx-room"))
                    ksp(libs.library("androidx-room-compiler"))
                    implementation(libs.library("androidx-room-ktx"))
                }
            }
        }
    }
}
