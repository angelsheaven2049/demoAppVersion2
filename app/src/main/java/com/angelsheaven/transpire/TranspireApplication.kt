package com.angelsheaven.transpire

import android.app.Activity
import android.app.Application
import com.angelsheaven.transpire.di.AppInjector
import com.angelsheaven.transpire.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Entry point for application
 *
 * @author Quan Nguyen
 */

open class TranspireApplication:Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    /**
     * Static fields
     */
    companion object {
        lateinit var appComponent: AppComponent
        lateinit var instance: TranspireApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        /*appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .storageModule(StorageModule())
            .build()*/

        AppInjector.init(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}
