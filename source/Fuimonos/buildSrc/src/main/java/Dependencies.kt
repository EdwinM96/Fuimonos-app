object Versions {
    const val gradle = "4.0.1"

    const val compileSdkVersion = 30
    const val minSdkVersion = 19
    const val targetSdkVersion = 29
    const val buildToolsVersion = "30.0.1"

    const val kotlin = "1.3.72"
    const val ktx = "1.3.0"

    const val appcompat = "1.1.0"
    const val constraintlayout = "1.1.3"

    const val koin = "2.1.6"

    const val lifecycle = "2.2.0"

    const val timber = "4.7.1"

    const val junit = "4.12"
    const val androidTest = "1.1.1"
    const val espresso = "3.2.0"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val dataBindingCompiler = "com.android.databinding:compiler:${Versions.gradle}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object SupportLibs {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
}

object LifecycleLibs {
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
}

object KoinLibs {
    const val koinCore = "org.koin:koin-core:${Versions.koin}"
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}

object TestLibs {
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTest = "androidx.test.ext:junit:${Versions.androidTest}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
