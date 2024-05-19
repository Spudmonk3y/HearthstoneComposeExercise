plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    alias(libs.plugins.gms.googleServices)
}

android {
    namespace = "com.monkeybased.hearthtechnicalexercise"
    compileSdk = 34

    val versionMajor = 1
    val versionMinor = 0

    defaultConfig {
        applicationId = "com.monkeybased.hearthtechnicalexercise"
        minSdk = 26
        targetSdk = 34
        versionCode = computeVersionCode(versionMajor, versionMinor)
        versionName = computeVersionName(versionMajor, versionMinor)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun computeVersionName(versionMajor: Int, versionMinor: Int): String {
    // Basic <major>.<minor>.<build> version name, will return "1.0.42" for runner build 42, or "1.0.0" if building on a machine without that set variable
    return String.format("%d.%d.%d", versionMajor, versionMinor, Integer.valueOf(System.getenv("BUILD_NUMBER") ?: "0"))
}

fun computeVersionCode(versionMajor: Int, versionMinor: Int): Int {
    // <major> + <minor> + <build> version code, will return 1042 for runner build 42, or 1000 if building on a machine without that set variable
    return (versionMajor * 1000) + (versionMinor * 100) + (Integer.valueOf(System.getenv("BUILD_NUMBER") ?: "0"))
}
val ktlintConfig: Configuration by configurations.creating

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.compose.viewmodel)
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gsonconverter)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.coil.compose)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    ktlintConfig(libs.ktlint)
}

val ktlint by tasks.registering(JavaExec::class) {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check for Kotlin code style issues"
    classpath = ktlintConfig
    mainClass.set("com.pinterest.ktlint.Main")

    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
    args(
        "**/src/**/*.kt",
        "**.kts"
    )
}

tasks.check {
    dependsOn(ktlint)
}

val ktlintFormat by tasks.registering(JavaExec::class) {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Fix Kotlin code style issues"
    classpath = ktlintConfig
    mainClass.set("com.pinterest.ktlint.Main")
    args(
        "-F",
        "**/src/**/*.kt",
        "**.kts"
    )
}