package br.com.seucaio.gamedex.di

import br.com.seucaio.gamedex.repository.GamesRepository
import br.com.seucaio.gamedex.repository.GenresRepository
import br.com.seucaio.gamedex.repository.PlatformsRepository
import io.mockk.mockk
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class DomainModuleTest : KoinTest {

    @Test
    fun `check all modules from domain`() {
        checkModules {
            modules(provideDomainModule(), module {
                single { mockk<PlatformsRepository>() }
                single { mockk<GenresRepository>() }
                single { mockk<GamesRepository>() }
            })
        }
    }
}