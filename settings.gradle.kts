pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "il.ac.technion.cs") {
                useModule("com.github.chaosite:reactivize-gradle-plugin:master-SNAPSHOT")
            }
        }
    }
    repositories {
        maven("https://dl.bintray.com/kotlin/kotlin-eap")

        mavenCentral()

        maven("https://plugins.gradle.org/m2/")

        maven("https://oss.sonatype.org/content/repositories/snapshots")
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