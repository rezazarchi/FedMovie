package ir.rezazarchi.fedmovie.featureflag

import ir.rezazarchi.fedmovie.featureflag.sdk.FirebaseFeatureFlag
import ir.rezazarchi.fedmovie.featureflag.sdk.GrowthBookFeatureFlag

object BasketFeature {

    fun isBasketVisible(): Boolean {
        val featureFlagManager = FeatureFlagManager()
        featureFlagManager.addProvider(GrowthBookFeatureFlag())
        featureFlagManager.addProvider(FirebaseFeatureFlag())
//        featureFlagManager.addProvider(UnleashFeatureFlag())
        return featureFlagManager.getValue("addtobasket")

    }

}