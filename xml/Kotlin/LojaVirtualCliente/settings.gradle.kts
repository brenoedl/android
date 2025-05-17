pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
                maven { setUrl ("https://artifacts.mercadolibre.com/repository/android-releases/") }
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
        maven { setUrl ("https://artifacts.mercadolibre.com/repository/android-releases/") }
    }
}

rootProject.name = "Loja Virtual Cliente"
include(":app")
