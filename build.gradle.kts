import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.detekt) apply false
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    afterEvaluate {
        extensions.configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
            toolVersion = libs.versions.detekt.get()
            config.setFrom(file("$rootDir/config/detekt/detekt.yml"))
            buildUponDefaultConfig = true
        }

        tasks.withType<Detekt>().configureEach {
            jvmTarget = "11"
            reports {
                html.required.set(true)
                xml.required.set(false)
                txt.required.set(false)
                sarif.required.set(false)
            }
        }
    }
}