package com.angelsheaven.transpire.di

import android.app.Application
import androidx.room.Room
import com.angelsheaven.transpire.data.storage.AppDatabase
import com.angelsheaven.transpire.data.storage.DatabaseContract
import com.angelsheaven.volley.NetworkController
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * App module provide context for any class in project
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    /**
     * Provide volley controller for retrieve data remotely
     * @param context of provided application
     */
    @Provides
    @Singleton
    fun provideVolleyController(app: Application): NetworkController = NetworkController(app)

    /**
     * Provide database instance
     * @param context of provided application
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