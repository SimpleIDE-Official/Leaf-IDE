object Versions {
    private const val version = "1.0"

    const val compileSdkVersion = 34
    const val targetSdkVersion = 34
    const val minSdkVersion = 21
    const val buildToolsVersion = "34.0.0"
    const val versionCode = 1
    val versionName by lazy {
        if (CI.isCiBuild) {
            "$version-${CI.commitHash}-SNAPSHOT"
        } else version
    }
}