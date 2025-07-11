pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "ConversionApp"
include(":app")

// core modules
include(":core:network")
include(":core:designsystem")
include(":core:datastore")
include(":core:database")

// feature modules
include(":feature:sync")
include(":feature:exchange")
include(":feature:balance")
include(":feature:comission")
include(":feature:settings")
include(":feature:transaction")
