package ir.rezazarchi.fedmovie.featureflag

import ir.rezazarchi.fedmovie.featureflag.sdk.FeatureFlagProvider

class FeatureFlagManager {

    private val providers: MutableList<FeatureFlagProvider> = mutableListOf()

    fun addProvider(provider: FeatureFlagProvider) {
        providers.add(provider)
    }

    fun getValue(key: String): Boolean {
        var flagExist = true
        for (provider in providers) {
            if (!provider.getValue(key)) {
                flagExist = false
                break
            }
        }
        return flagExist
    }

}