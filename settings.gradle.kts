pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "il.ac.technion.cs") {
                useModule("com.github.chaosite:reactivize-gradle-plugin:master-SNAPSHOT")
            }
        }
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://soot-build.cs.upb.de/nexus/repository/soot-release/")
        maven("https://jitpack.io") {
            metadataSources {
                gradleMetadata()
                mavenPom()
            }
        }
    }
}
rootProject.name = "reactivize-sample"

// includeBuild("../reactivize-gradle-plugin")