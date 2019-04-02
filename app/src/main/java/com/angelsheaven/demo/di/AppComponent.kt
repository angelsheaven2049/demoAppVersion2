package com.angelsheaven.demo.di

import android.app.Application
import com.angelsheaven.demo.TranspireApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * An interface to inject dependencies to viewmodel classes
 * in project
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class]
)
interface AppComponent{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(transpireApplication: TranspireApplication)

}
