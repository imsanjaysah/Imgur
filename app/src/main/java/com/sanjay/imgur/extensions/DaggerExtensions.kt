/*
 * DaggerExtensions.kt
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.extensions
import android.content.Context
import com.sanjay.imgur.ImgurApplication

/**
 * Created by Sanjay Sah
 */

val Context.appComponent
    get() = (applicationContext as ImgurApplication).appComponent
