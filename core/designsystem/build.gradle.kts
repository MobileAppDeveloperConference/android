@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("conf.mad.convention.android.feature")
}

android {
    namespace = "com.conf.mad.todo.designsystem"
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}
