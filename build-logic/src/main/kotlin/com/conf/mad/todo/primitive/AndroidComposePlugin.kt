package com.conf.mad.todo.primitive

import com.conf.mad.todo.dsl.debugImplementation
import com.conf.mad.todo.dsl.implementation
import com.conf.mad.todo.dsl.implementationPlatform
import com.conf.mad.todo.dsl.library
import com.conf.mad.todo.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

@Suppress("unused")
class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            val projectPath = rootProject.file(".").absolutePath

            extensions.getByType<KotlinAndroidProjectExtension>().apply {
                compilerOptions {
                    freeCompilerArgs.set(
                        freeCompilerArgs.getOrElse(emptyList()) + listOf(
                            "-P",
                            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=$projectPath/report/compose-metrics"
                        ) + listOf(
                            "-P",
                            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=$projectPath/report/compose-reports"
                        )
                    )
                }
            }

            extensions.getByType<ComposeCompilerGradlePluginExtension>().apply {
                enableStrongSkippingMode.set(true)
                includeSourceInformation.set(true)
            }

            dependencies {
                implementation(libs.library("androidx-core"))
                implementationPlatform(libs.library("androidx-compose-bom"))
                implementation(libs.library("androidx-compose-activity"))
                implementation(libs.library("androidx-compose-hilt-navigation"))
                implementation(libs.library("androidx-compose-lifecycle"))
                implementation(libs.library("androidx-compose-navigation"))
                implementation(libs.library("androidx-compose-ui"))
                implementation(libs.library("androidx-compose-ui-foundation"))
                implementation(libs.library("androidx-compose-material3"))
                implementation(libs.library("androidx-lifecycle-runtime"))
                implementation(libs.library("androidx-compose-ui-tooling"))
                debugImplementation(libs.library("androidx-compose-ui-test-manifest"))
            }
        }
    }
}
