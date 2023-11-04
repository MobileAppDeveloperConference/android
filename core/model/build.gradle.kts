@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("conf.mad.convention.android.feature")
}

android {
    namespace = "com.conf.mad.todo.model"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}
