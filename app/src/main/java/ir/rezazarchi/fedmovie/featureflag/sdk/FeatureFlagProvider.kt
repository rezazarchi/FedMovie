package ir.rezazarchi.fedmovie.featureflag.sdk

interface FeatureFlagProvider {
    fun getValue(key: String): Boolean
}