package ir.rezazarchi.fedmovie

import android.app.Application
import ir.rezazarchi.fedmovie.data.di.networkModule
import ir.rezazarchi.fedmovie.presentation.di.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup.onKoinStartup

@Suppress("OPT_IN_USAGE")
class MainApplication : Application() {

    init {
        onKoinStartup {
            androidContext(this@MainApplication)
            modules(networkModule, presentationModules)
        }
    }

}