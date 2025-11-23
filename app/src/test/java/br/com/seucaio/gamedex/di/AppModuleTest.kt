package br.com.seucaio.gamedex.di

import br.com.seucaio.gamedex.usecase.game.GetGameDetailByIdUseCase
import br.com.seucaio.gamedex.usecase.game.GetGamesByPlatformByIdUseCase
import br.com.seucaio.gamedex.usecase.platform.GetPlatformDetailByIdUseCase
import br.com.seucaio.gamedex.usecase.platform.GetPlatformsUseCase
import io.mockk.mockk
import io.mockk.mockkClass
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProvider

class AppModuleTest : KoinTest {

    @Test
    fun `check all modules from app`() {
        // Register a MockProvider so Koin can provide parameters & dependencies during checkModules
        MockProvider.register { clazz ->
            when (clazz) {
                Int::class -> 0
                String::class -> ""
                else -> mockkClass(clazz, relaxed = true)
            }
        }
        checkModules {
            modules(
                appModule,
                module {
                    // Provide the UseCases required by the ViewModels in appModule
                    single { mockk<GetPlatformsUseCase>(relaxed = true) }
                    single { mockk<GetPlatformDetailByIdUseCase>(relaxed = true) }
                    single { mockk<GetGamesByPlatformByIdUseCase>(relaxed = true) }
                    single { mockk<GetGameDetailByIdUseCase>(relaxed = true) }
                }
            )
        }
    }
}
