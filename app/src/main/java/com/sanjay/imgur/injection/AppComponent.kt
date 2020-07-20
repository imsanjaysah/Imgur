/*
 * AppComponent.kt
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.injection


import com.sanjay.imgur.injection.module.*
import dagger.Component
import javax.inject.Singleton

/**
 * @author Sanjay Sah
 */

@Singleton
@Component(modules = [DatabaseModule::class, AppModule::class, ViewModelModule::class, RepositoryModule::class, ApiServiceModule::class, SchedulerModule::class])
interface AppComponent {

    fun activityModule(activityModule: ActivityModule): ActivityComponent

    fun fragmentModule(fragmentModule: FragmentModule): FragmentComponent


}