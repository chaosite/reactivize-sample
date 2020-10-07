plugins {
    kotlin("jvm") version "1.3.72"
    id("il.ac.technion.cs.reactivize-gradle-plugin") version "1.0-SNAPSHOT"
}

group = "il.ac.technion.cs"
version = "1.0-SNAPSHOT"



repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
    jcenter()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://soot-build.cs.upb.de/nexus/repository/soot-release/")
    maven("https://jitpack.io") {
        metadataSources {
            gradleMetadata()
            mavenPom()
        }
    }

}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(1, TimeUnit.MINUTES)
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("com.github.chaosite", "reactivize-core", "master-SNAPSHOT").apply {
        isChanging = true
    }
    implementation("com.github.chaosite", "reactivize-core", "master-SNAPSHOT").apply {
        isChanging = true
    }
    implementation("io.reactivex.rxjava3", "rxjava", "3.0.4")
    implementation("io.reactivex.rxjava3", "rxkotlin", "3.0.0")
    implementation("org.apache.bcel", "bcel", "6.5.0")

    // for finance api
    implementation("com.yahoofinance-api", "YahooFinanceAPI", "3.15.0")
}

tasks.register<JavaExec>("runVerifier") {
    group = "Execution"
    description = "Verify a class file"
    classpath = sourceSets.main.get().runtimeClasspath
    main = "org.apache.bcel.verifier.Verifier"
    workingDir = File("build/classes/kotlin/main")
}

configure<il.ac.technion.cs.reactivize.gradle.ReactivizeGradlePluginConfiguration> {
    packagePrefixes = listOf("il.ac.technion.cs.reactivize.sample", "yahoofinance")
}