package br.com.seucaio.gamedex.di

import br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel.PlatformDetailsViewModel
import br.com.seucaio.gamedex.ui.features.platforms.list.viewmodel.PlatformListViewModel
import br.com.seucaio.gamedex.usecase.platform.GetPlatformDetailByIdUseCase
import br.com.seucaio.gamedex.usecase.platform.GetPlatformsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domainModule = provideDomainModule()

val dataModule = provideDataModule()

val appModule = module {
    viewModel {
        PlatformListViewModel(getPlatformsUseCase = get<GetPlatformsUseCase>())
    }
    viewModel { (id: Int) ->
        PlatformDetailsViewModel(
            id = id,
            getPlatformByIdUseCase = get<GetPlatformDetailByIdUseCase>(),
        )
    }
}

val gameDexModules = listOf(
    domainModule,
    dataModule,
    appModule
)