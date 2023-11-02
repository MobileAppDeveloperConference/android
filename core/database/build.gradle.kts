@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("conf.mad.convention.android.feature")
    id("conf.mad.primitive.android.room")
}

android {
    namespace = "com.conf.mad.todo.database"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}
