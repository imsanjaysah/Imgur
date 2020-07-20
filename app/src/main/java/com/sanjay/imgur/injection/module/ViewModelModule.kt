/*
 * ViewModelModule.kt
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sanjay.imgur.injection.ViewModelFactory
import com.sanjay.imgur.ui.detail.ImgurImageDetailViewModel
import com.sanjay.imgur.ui.search.ImgurSearchViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
open class ViewModelModule {

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @IntoMap
    @ViewModelFactory.ViewModelKey(ImgurSearchViewModel::class)
    fun providesImgurSearchViewModel(viewModel: ImgurSearchViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelFactory.ViewModelKey(ImgurImageDetailViewModel::class)
    fun providesImageDetailViewModel(viewModel: ImgurImageDetailViewModel): ViewModel = viewModel

}