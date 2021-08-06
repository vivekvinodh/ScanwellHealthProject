package com.example.scanwellhealthproject.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Module providing retrofit
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val baseUrl = "https://api.stackexchange.com/"

    /**
     * Provides retrofit
     *
     * @return Retrofit
     */
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}