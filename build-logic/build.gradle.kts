import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.conf.mad.todo.buildlogic"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "17"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
    // https://github.com/google/dagger/issues/3068#issuecomment-1470534930
    implementation(libs.java.poet)
}

gradlePlugin {
    plugins {
        // primitives
        register("androidApplication") {
            id = "conf.mad.primitive.android.application"
            implementationClass = "com.conf.mad.todo.primitive.AndroidApplicationPlugin"
        }
        register("androidCompose") {
            id = "conf.mad.primitive.android.compose"
            implementationClass = "com.conf.mad.todo.primitive.AndroidComposePlugin"
        }
        register("androidHilt") {
            id = "conf.mad.primitive.android.hilt"
            implementationClass = "com.conf.mad.todo.primitive.AndroidHiltPlugin"
        }
        register("androidRoom") {
            id = "conf.mad.primitive.android.room"
            implementationClass = "com.conf.mad.todo.primitive.AndroidRoomPlugin"
        }
        register("androidKotlin") {
            id = "conf.mad.primitive.android.kotlin"
            implementationClass = "com.conf.mad.todo.primitive.AndroidKotlinPlugin"
        }
        register("android") {
            id = "conf.mad.primitive.android"
            implementationClass = "com.conf.mad.todo.primitive.AndroidPlugin"
        }
        register("kotlinSerialization") {
            id = "conf.mad.primitive.kotlin.serialization"
            implementationClass = "com.conf.mad.todo.primitive.KotlinSerializationPlugin"
        }
        // convention
        register("androidFeature") {
            id = "conf.mad.convention.android.feature"
            implementationClass = "com.conf.mad.todo.convention.AndroidFeaturePlugin"
        }
        register("pureKotlin") {
            id = "conf.mad.convention.kotlin"
            implementationClass = "com.conf.mad.todo.convention.PureKotlinPlugin"
        }
    }
}
