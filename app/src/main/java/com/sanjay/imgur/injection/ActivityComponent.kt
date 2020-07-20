/*
 * ActivityComponent.kt
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.injection

import com.sanjay.imgur.ui.search.ImgurSearchActivity
import com.sanjay.imgur.injection.annotations.PerActivity
import com.sanjay.imgur.injection.module.ActivityModule
import dagger.Subcomponent

/**
 * @author Sanjay Sah
 */

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: ImgurSearchActivity)


}