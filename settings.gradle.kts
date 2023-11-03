enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TodoMad"
include(
    ":app",
    ":core:database",
    ":core:designsystem",
    ":core:model",
    ":data:task",
    ":core:ui",
    ":feature:home",
    ":feature:post"
)
