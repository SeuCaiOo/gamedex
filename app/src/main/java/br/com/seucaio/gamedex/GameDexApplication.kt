package br.com.seucaio.gamedex

import android.app.Application
import br.com.seucaio.gamedex.di.gameDexModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

open class GameDexApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        configureKoin()
    }

    private fun configureKoin() {
        startKoin {
            androidLogger()
            androidContext(this@GameDexApplication)
            modules(gameDexModules)
        }
    }
}