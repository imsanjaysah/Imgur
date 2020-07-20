/*
 * FragmentComponent.kt
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.injection

import com.sanjay.imgur.injection.module.FragmentModule
import com.sanjay.imgur.ui.detail.ImgurDetailImageFragment
import com.sanjay.imgur.ui.search.ImgurImagesFragment
import dagger.Subcomponent

/**
 * @author Sanjay Sah
 */

@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(fragment: ImgurImagesFragment)
    fun inject(fragment: ImgurDetailImageFragment)
}