dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Meals"
include(":app", ":features", ":features:recipes", ":features:home",
    ":features:favourites", ":libraries", ":libraries:data", ":libraries:remote",
    ":libraries:navigation", ":libraries:domain", ":common", ":features:ingredients",
    ":common:utils", ":common:view", ":features:launch")
