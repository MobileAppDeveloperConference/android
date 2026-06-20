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
            // AGP 9 enables built-in Kotlin by default; the kotlin-android plugin is
            // incompatible with the new DSL, so it is no longer applied here.
            extensions.getByType<KotlinAndroidProjectExtension>().apply {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)

                    freeCompilerArgs.set(
                        freeCompilerArgs.getOrElse(emptyList()) + listOf(
                            "-opt-in=kotlin.RequiresOptIn",
                            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
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
