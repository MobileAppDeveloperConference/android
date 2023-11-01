package com.conf.mad.todo.primitive

import com.conf.mad.todo.dsl.android
import com.conf.mad.todo.dsl.bundle
import com.conf.mad.todo.dsl.debugImplementation
import com.conf.mad.todo.dsl.implementation
import com.conf.mad.todo.dsl.implementationPlatform
import com.conf.mad.todo.dsl.library
import com.conf.mad.todo.dsl.libs
import com.conf.mad.todo.dsl.testImplementation
import com.conf.mad.todo.dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            android {
                buildFeatures.compose = true
                composeOptions {
                    kotlinCompilerExtensionVersion = libs.version("composeCompiler")
                }
            }
            dependencies {
                implementation(libs.library("androidx-core"))
                implementationPlatform(libs.library("androidx-compose-bom"))
                implementationPlatform(libs.library("androidx-compose-activity"))
                implementation(libs.library("androidx-compose-ui"))
                implementation(libs.library("androidx-compose-ui-foundation"))
                implementation(libs.library("androidx-compose-material3"))
                implementation(libs.library("androidxLifecycleLifecycleRuntimeKtx"))
                implementation(libs.library("androidxActivityActivityCompose"))
                debugImplementation(libs.library("androidx-compose-ui-tooling"))
                debugImplementation(libs.library("androidx-compose-ui-test-manifest"))
            }
        }
    }
}
