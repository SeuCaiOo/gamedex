package br.com.seucaio.gamedex.di

import android.content.Context
import br.com.seucaio.gamedex.core.network.ConnectivityChecker
import io.mockk.mockk
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class DataModuleTest : KoinTest {

    @Test
    fun `check all modules from data`() {
        checkModules {
            // Mock Android Context dependency, which is required by the database module
            modules(
                provideDataModule(),
                module {
                    single { mockk<Context>(relaxed = true) }
                    single { mockk<ConnectivityChecker>(relaxed = true) }
                }
            )
        }
    }
}
