package com.conf.mad.todo.primitive

import com.conf.mad.todo.dsl.implementation
import com.conf.mad.todo.dsl.library
import com.conf.mad.todo.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

@Suppress("unused")
class AndroidKotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }

            extensions.getByType<KotlinAndroidProjectExtension>().apply {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)

                    freeCompilerArgs.set(
                        freeCompilerArgs.getOrElse(emptyList()) + listOf(
                            "-opt-in=kotlin.RequiresOptIn",
                            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                            "-Xcontext-receivers"
                        )
                    )
                }
            }

            dependencies {
                implementation(libs.library("kotlinx-coroutines-core"))
                implementation(libs.library("kotlinx-collections-immutable"))
            }
        }
    }
}
