plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    // ... your existing settings ...

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs.add("-Xbackend-threads=0")
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.10.0")
    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
}
