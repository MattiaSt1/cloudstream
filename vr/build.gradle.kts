plugins {
    id("com.android.library")
}

android {
    namespace = "com.lagradost.cloudstream3.vr"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
}
