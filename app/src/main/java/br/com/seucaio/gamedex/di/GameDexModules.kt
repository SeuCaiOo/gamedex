package br.com.seucaio.gamedex.di

val domainModule = provideDomainModule()

val dataModule = provideDataModule()

val gameDexModules = listOf(
    domainModule,
    dataModule
)