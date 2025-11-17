package br.com.seucaio.gamedex

import leakcanary.LeakCanary

class DebugApplication : GameDexApplication() {
    override fun onCreate() {
        super.onCreate()
        LeakCanary.config = LeakCanary.config.copy(
            retainedVisibleThreshold = 3,
            dumpHeap = true,
            requestWriteExternalStoragePermission = false,
            useExperimentalLeakFinders = true
        )
    }
}