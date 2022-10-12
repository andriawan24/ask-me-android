object Versions {
    const val coreKtxVersion = "1.9.0"
    const val appCompatVersion = "1.5.1"
    const val materialVersion = "1.6.1"
    const val constraintLayoutVersion = "2.1.4"
    const val navigationVersion = "2.5.2"
    const val dataStoreVersion = "1.0.0"

    // Testing
    const val jUnitVersion = "4.13.2"
    const val extJUnitVersion = "1.1.3"
    const val espressoVersion = "3.4.0"
}

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"

    // Navigation Component
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

    // Preferences Data Store
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStoreVersion}"

    // Testing
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val extJUnit = "androidx.test.ext:junit:${Versions.extJUnitVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
}