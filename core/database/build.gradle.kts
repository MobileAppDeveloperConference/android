@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("conf.mad.convention.android.feature")
    id("conf.mad.primitive.android.room")
}

android {
    namespace = "com.conf.mad.todo.database"

    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
        consumerProguardFiles("consumer-rules.pro")
    }
}
