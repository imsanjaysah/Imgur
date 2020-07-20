/*
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.injection.module

import com.sanjay.imgur.data.repository.ImgurDataSource
import com.sanjay.imgur.data.repository.local.ImgurLocalDataSource
import com.sanjay.imgur.data.repository.remote.ImgurRemoteDataSource
import com.sanjay.imgur.injection.annotations.Local
import com.sanjay.imgur.injection.annotations.Remote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Sanjay Sah
 */

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Local
    fun providesLocalDataSource(localDataSource: ImgurLocalDataSource): ImgurDataSource =
        localDataSource

    @Provides
    @Singleton
    @Remote
    fun providesRemoteDataSource(remoteDataSource: ImgurRemoteDataSource): ImgurDataSource =
        remoteDataSource

}