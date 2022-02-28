package com.elsawy.edvora.di

import android.content.Context
import com.elsawy.edvora.data.local.PreferencesRepository
import com.elsawy.edvora.data.network.ApiService
import com.elsawy.edvora.data.network.RidesRepository
import com.elsawy.edvora.data.network.UserRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
   private const val BASE_URL = "https://assessment.api.vweb.app"

   @Singleton
   @Provides
   fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
      .readTimeout(100, TimeUnit.SECONDS)
      .connectTimeout(100, TimeUnit.SECONDS)
      .build()

   @Singleton
   @Provides
   fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create(
         GsonBuilder().setLenient().create())
      ).build()

   @Singleton
   @Provides
   fun provideApiService(retrofit: Retrofit): ApiService =
      retrofit.create(ApiService::class.java)

   @Singleton
   @Provides
   fun providesUserRepository(apiService: ApiService) = UserRepository(apiService)

   @Singleton
   @Provides
   fun providesRidesRepository(apiService: ApiService) = RidesRepository(apiService)

   @Singleton
   @Provides
   fun providesUserPreferencesRepository(@ApplicationContext appContext: Context) =
      PreferencesRepository.getInstance(appContext)
}