plugins {
    // android
    id("com.android.library")
    // jetbrains
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {

    namespace = "ir.farsroidx.m31"
    compileSdk = 34

    defaultConfig {
        multiDexEnabled = true
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        debug {
            isMinifyEnabled = false
        }

        release {
            multiDexEnabled = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    lint {
        baseline = file("lint-baseline.xml")
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Material
    implementation("com.google.android.material:material:1.9.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Koin
    implementation("io.insert-koin:koin-android:3.5.0")
    implementation("io.insert-koin:koin-ktor:3.5.1")

    // Room Database
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")

    // Ktor
    implementation("io.ktor:ktor-client-okhttp:2.3.5")

    // Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Google
    implementation("com.google.code.gson:gson:2.10.1")

    // Others
    implementation("androidx.drawerlayout:drawerlayout:1.2.0")
    implementation("androidx.customview:customview:1.1.0")

    api("com.airbnb.android:lottie:6.1.0")
    api("com.github.samanzamani:PersianDate:1.6.1")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // =-------------------------------------------------------------------------------------------=
    // =--= Unit Test =-------------------------------------------------------------= Unit Test =--=
    // =-------------------------------------------------------------------------------------------=

    // Unit Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}