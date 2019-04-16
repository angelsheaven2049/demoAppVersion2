package com.angelsheaven.demo.di

import android.app.Application
import androidx.room.Room
import com.angelsheaven.demo.data.network.NetworkContract
import com.angelsheaven.demo.data.network.retrofit.ArticleService
import com.angelsheaven.demo.data.network.volley.NetworkController
import com.angelsheaven.demo.data.storage.AppDatabase
import com.angelsheaven.demo.data.storage.DatabaseContract
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * App module provide context for any class in project
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    /**
     * Provide volley controller for retrieve data remotely
     * @param app of provided application
     */
    @Provides
    @Singleton
    fun provideVolleyController(app: Application): NetworkController = NetworkController(app)

    @Provides
    @Singleton
    fun provideArticleService():ArticleService = Retrofit.Builder()
        .baseUrl(NetworkContract.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(ArticleService::class.java)

    /**
     * Provide database instance
     * @param app of provided application
     */
    @Provides
    @Singleton
    fun provideAppDatabase(app: Application) =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            DatabaseContract.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

}