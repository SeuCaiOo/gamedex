package br.com.seucaio.gamedex.di

import br.com.seucaio.gamedex.repository.GenresRepository
import br.com.seucaio.gamedex.repository.PlatformsRepository
import br.com.seucaio.gamedex.usecase.genre.GetGenreDetailByIdUseCase
import br.com.seucaio.gamedex.usecase.genre.GetGenresUseCase
import br.com.seucaio.gamedex.usecase.platform.GetPlatformDetailByIdUseCase
import br.com.seucaio.gamedex.usecase.platform.GetPlatformsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetGenresUseCase(repository = get<GenresRepository>()) }
    factory { GetGenreDetailByIdUseCase(repository = get<GenresRepository>()) }
    factory { GetPlatformsUseCase(repository = get<PlatformsRepository>()) }
    factory { GetPlatformDetailByIdUseCase(repository = get<PlatformsRepository>()) }
}

val dataModule = provideDataModule()

val gameDexModules = listOf(
    domainModule,
    dataModule
)