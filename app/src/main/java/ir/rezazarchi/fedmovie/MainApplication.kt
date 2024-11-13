package ir.rezazarchi.fedmovie

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup.onKoinStartup

@Suppress("OPT_IN_USAGE")
class MainApplication : Application() {

    init {
        onKoinStartup {
            androidContext(this@MainApplication)
        }
    }

}