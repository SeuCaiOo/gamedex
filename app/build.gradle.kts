plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlinx.kover)
}

android {
    namespace = "br.com.seucaio.gamedex"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "br.com.seucaio.gamedex"
        minSdk = 24
        targetSdk = 36
        versionCode = 2
        versionName = "1.1.0-alpha"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE-notice.md}"
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.coil)

    testImplementation(libs.bundles.unitTest)
    testImplementation(libs.bundles.unitTestAndroid)
    testImplementation(libs.androidx.compose.ui.test.junit4)
    testImplementation(libs.robolectric)
    testImplementation(libs.bundles.koinTest)

    androidTestImplementation(libs.bundles.androidTest)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.navigation.testing)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    detektPlugins(libs.bundles.detekt)
}

detekt {
    config.setFrom(
        files(
            "$rootDir/config/detekt/detekt.yml",
            "$rootDir/config/detekt/detekt-compose.yml"
        )
    )
    toolVersion = libs.versions.detekt.get()
    buildUponDefaultConfig = true
    ignoreFailures = true
}

// Configure Kover to generate an aggregated report for all modules.
kover {
    dependencies {
        kover(project(":domain"))
        kover(project(":data"))
    }

    reports {
        total {
            log { onCheck = true }
            html { onCheck = true }
            xml { onCheck = true }

            filters {
                excludes {
                    classes(
                        "*BuildConfig*",
                        "*.R",
                        "*.R$*",
                        "*Composable*",
                        "*Screen*",
                        "*NavGraph*",
                        "*Route*",
                        "*Application*",
                        "*Activity*"
                    )
                    packages(
                        "*.ui.theme",
                        "*.ui.navigation",
                        "**.screen**"
                    )
                    annotatedBy(
                        "androidx.compose.ui.tooling.preview.Preview",
                        "androidx.compose.ui.tooling.preview.PreviewLightDark"
                    )
                }
                includes {}
            }

            verify {
                warningInsteadOfFailure = true
                rule("Rule of coverage minimum for the project") {
                    minBound(60)
                }
            }
        }
    }
}
