package br.com.seucaio.gamedex.di

import br.com.seucaio.gamedex.remote.service.GameDexApiService
import br.com.seucaio.gamedex.remote.service.RetrofitConfig
import br.com.seucaio.gamedex.remote.service.interceptor.NetworkInterceptor
import br.com.seucaio.gamedex.remote.source.PlatformsRemoteDataSource
import br.com.seucaio.gamedex.remote.source.PlatformsRemoteDataSourceImpl
import br.com.seucaio.gamedex.repository.PlatformsRepository
import br.com.seucaio.gamedex.repository.PlatformsRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

fun provideDataModule() = module {

    // region Network
    single<HttpLoggingInterceptor> { NetworkInterceptor.loggingInterceptor() }
    single<OkHttpClient> {
        RetrofitConfig.okHttpClient(interceptors = listOf(get<HttpLoggingInterceptor>()))
    }
    single<Retrofit> { RetrofitConfig.providesRetrofit(okHttpClient = get<OkHttpClient>()) }
    single<GameDexApiService> { get<Retrofit>().create(GameDexApiService::class.java) }
    // endregion

    // region Data Source
    single<PlatformsRemoteDataSource> {
        PlatformsRemoteDataSourceImpl(apiService = get<GameDexApiService>())
    }
    // endregion

    single<PlatformsRepository> {
        PlatformsRepositoryImpl(remoteDataSource = get<PlatformsRemoteDataSource>())
    }
}