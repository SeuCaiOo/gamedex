package br.com.seucaio.gamedex.di

import br.com.seucaio.gamedex.ui.features.games.details.viewmodel.GameDetailsViewModel
import br.com.seucaio.gamedex.ui.features.games.list.viewmodel.GameListViewModel
import br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel.PlatformDetailsViewModel
import br.com.seucaio.gamedex.ui.features.platforms.list.viewmodel.PlatformListViewModel
import br.com.seucaio.gamedex.usecase.game.GetGameDetailByIdUseCase
import br.com.seucaio.gamedex.usecase.game.GetGamesByPlatformByIdUseCase
import br.com.seucaio.gamedex.usecase.platform.GetPlatformDetailByIdUseCase
import br.com.seucaio.gamedex.usecase.platform.GetPlatformsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domainModule = provideDomainModule()

val dataModule = provideDataModule()

val appModule = module {
    // region Platforms

    viewModel {
        PlatformListViewModel(getPlatformsUseCase = get<GetPlatformsUseCase>())
    }
    viewModel { (id: Int) ->
        PlatformDetailsViewModel(
            id = id,
            getPlatformByIdUseCase = get<GetPlatformDetailByIdUseCase>(),
        )
    }

    // endregion

    // region Games

    viewModel { (platformId: Int, gameQuery: String) ->
        GameListViewModel(
            platformId = platformId,
            gameQuery = gameQuery,
            getGamesByPlatformByIdUseCase = get<GetGamesByPlatformByIdUseCase>(),
        )
    }
    viewModel { (gameId: Int) ->
        GameDetailsViewModel(
            id = gameId,
            getGameDetailByIdUseCase = get<GetGameDetailByIdUseCase>(),
        )
    }

    // endregion
}

val gameDexModules = listOf(
    domainModule,
    dataModule,
    appModule
)
