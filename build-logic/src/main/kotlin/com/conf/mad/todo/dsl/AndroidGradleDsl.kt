package com.conf.mad.todo.dsl

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

fun Project.androidApplication(action: ApplicationExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}

// CommonExtension lost its type parameters in AGP 9; use the raw type and configure shared settings here.
fun Project.android(action: CommonExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.setupAndroid() {
    android {
        // AGP 9 removed the block-method overloads on the raw CommonExtension, so configure
        // each nested DSL through its property with apply { } instead of the block form.
        namespace?.let {
            this.namespace = it
        }
        // Bumped AndroidX deps (core 1.19.0, lifecycle 2.11.0) require minCompileSdk 37.
        // This is compile-time only; targetSdk stays 36.
        compileSdk = 37

        defaultConfig.apply {
            minSdk = 30
            // targetSdk is only meaningful for app modules and was removed from the common
            // DefaultConfig in AGP 9; it is set in the application module's build file instead.
        }

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            isCoreLibraryDesugaringEnabled = true
        }
        testOptions.unitTests {
            isIncludeAndroidResources = true
        }
    }
    // The new public CommonExtension has no `dependencies` member, so add desugaring on the Project scope.
    dependencies {
        add("coreLibraryDesugaring", libs.library("android-desugar-libs"))
    }
}
