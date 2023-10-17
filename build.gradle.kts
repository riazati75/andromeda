buildscript {

    repositories {
        google()
    }

    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.1")
    }
}

plugins {
    // android
    id("com.android.application") version "8.3.0-alpha01" apply false
    id("com.android.library") version "8.3.0-alpha01" apply false
    // jetbrains
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    // google
    id("com.google.dagger.hilt.android") version "2.48" apply false
}