plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.kotlinx.kover)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {

    implementation(libs.koin.core)

    testImplementation(libs.bundles.unitTest)
    testImplementation(libs.bundles.koinTest)

    detektPlugins(libs.detekt.formatting)
}

detekt {
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    toolVersion = libs.versions.detekt.get()
    buildUponDefaultConfig = true
    ignoreFailures = true
}