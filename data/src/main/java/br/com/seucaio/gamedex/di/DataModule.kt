package br.com.seucaio.gamedex.di

import br.com.seucaio.gamedex.core.network.ConnectivityChecker
import br.com.seucaio.gamedex.local.database.GameDexDatabase
import br.com.seucaio.gamedex.local.database.dao.PlatformsDao
import br.com.seucaio.gamedex.local.database.dao.TopGamesDao
import br.com.seucaio.gamedex.local.source.PlatformsLocalDataSource
import br.com.seucaio.gamedex.local.source.PlatformsLocalDataSourceImpl
import br.com.seucaio.gamedex.remote.service.GameDexApiService
import br.com.seucaio.gamedex.remote.service.RetrofitConfig
import br.com.seucaio.gamedex.remote.service.interceptor.NetworkInterceptor
import br.com.seucaio.gamedex.remote.source.GamesRemoteDatSource
import br.com.seucaio.gamedex.remote.source.GamesRemoteDataSourceImpl
import br.com.seucaio.gamedex.remote.source.PlatformsRemoteDataSource
import br.com.seucaio.gamedex.remote.source.PlatformsRemoteDataSourceImpl
import br.com.seucaio.gamedex.repository.GamesRepository
import br.com.seucaio.gamedex.repository.GamesRepositoryImpl
import br.com.seucaio.gamedex.repository.PlatformsRepository
import br.com.seucaio.gamedex.repository.PlatformsRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit

fun provideDataModule() = module {

    // region Network
    single { ConnectivityChecker(androidApplication()) }
    single<HttpLoggingInterceptor> { NetworkInterceptor.loggingInterceptor() }
    single<OkHttpClient> {
        RetrofitConfig.okHttpClient(interceptors = listOf(get<HttpLoggingInterceptor>()))
    }
    single<Retrofit> { RetrofitConfig.providesRetrofit(okHttpClient = get<OkHttpClient>()) }
    single<GameDexApiService> { get<Retrofit>().create(GameDexApiService::class.java) }
    // endregion

    // region Database
    single<GameDexDatabase> { GameDexDatabase.getDatabase(context = get()) }
    single<PlatformsDao> { get<GameDexDatabase>().platformsDao() }
    single<TopGamesDao> { get<GameDexDatabase>().topGamesDao() }
    // endregion

    // region Data Source
    single<PlatformsRemoteDataSource> {
        PlatformsRemoteDataSourceImpl(
            apiService = get<GameDexApiService>(),
            connectivityChecker = get<ConnectivityChecker>()
        )
    }
    single<PlatformsLocalDataSource> {
        PlatformsLocalDataSourceImpl(dao = get<PlatformsDao>(), topGamesDao = get<TopGamesDao>())
    }

    single<GamesRemoteDatSource> {
        GamesRemoteDataSourceImpl(
            apiService = get<GameDexApiService>(),
            connectivityChecker = get<ConnectivityChecker>()
        )
    }
    // endregion

    single<PlatformsRepository> {
        PlatformsRepositoryImpl(
            remoteDataSource = get<PlatformsRemoteDataSource>(),
            localDataSource = get<PlatformsLocalDataSource>()
        )
    }

    single<GamesRepository> {
        GamesRepositoryImpl(remoteDataSource = get<GamesRemoteDatSource>())
    }
}