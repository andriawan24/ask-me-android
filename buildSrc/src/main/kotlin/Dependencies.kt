object Versions {
    const val coreKtxVersion = "1.9.0"
    const val composeVersion = "2022.12.00"
    const val composeActivityVersion = "1.6.1"
    const val composeViewModelVersion = "2.5.1"
    const val hiltNavigationComposeVersion = "1.0.0"
    const val navigationVersion = "2.5.3"
    const val dataStoreVersion = "1.0.0"
    const val retrofitVersion = "2.9.0"
    const val okHttpVersion = "4.10.0"
    const val hiltVersion = "2.44.2"
    const val shimmerVersion = "0.5.0"
    const val timberVersion = "5.0.1"

    // Testing
    const val jUnitVersion = "4.13.2"
    const val extJUnitVersion = "1.1.3"
    const val espressoVersion = "3.4.0"
    const val mockitoVersion = "4.11.0"
    const val ioMockkVersion = "1.13.3"
}

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"

    // Compose
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeVersion}"
    const val composeMaterial3 = "androidx.compose.material:material"
    const val composeMaterialIconExtended = "androidx.compose.material:material-icons-extended"
    const val composeFoundation = "androidx.compose.foundation:foundation"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.navigationVersion}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"

    // View Model and Activity
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModelVersion}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivityVersion}"

    // Preferences Data Store
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStoreVersion}"

    // Okhttp
    const val okHttpBom = "com.squareup.okhttp3:okhttp-bom:${Versions.okHttpVersion}"
    const val okHttpClient = "com.squareup.okhttp3:okhttp"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGSONConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"

    // Hilt Dagger
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationComposeVersion}"

    // Shimmer
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmerVersion}"

    // Timber for logging
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    // Testing
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val extJUnit = "androidx.test.ext:junit:${Versions.extJUnitVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockitoVersion}"
    const val ioMockk = "io.mockk:mockk:${Versions.ioMockkVersion}"
}