/*
 * AppModule.kt
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.injection.module


import android.content.Context
import com.sanjay.imgur.ImgurApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Sanjay Sah
 */

@Module
open class AppModule(private val application: ImgurApplication) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providesApplication(): ImgurApplication = application

}