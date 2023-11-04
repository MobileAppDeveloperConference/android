@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("conf.mad.primitive.android")
    id("conf.mad.primitive.android.kotlin")
    id("conf.mad.primitive.android.hilt")
}

android {
    namespace = "com.conf.mad.todo.common"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}
