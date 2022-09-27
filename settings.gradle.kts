pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven(url = "https://jitpack.io")
        jcenter()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven(url = "https://jitpack.io")
        jcenter()
    }
}
rootProject.name = "AvengersChat"
include(":app")
include(":core-model")
include(":core-network")
include(":core-database")
include(":core-data")
include(":core-uicomponents")
include(":feature-dm")
include(":feature-chat")
include(":feature-home")
include(":feature-home-common")
include(":feature-user")
include(":feature-live")
include(":feature-mention")
