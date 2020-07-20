/*
 * RxExtensions.kt
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.extensions

import android.text.TextUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Sanjay.Sah
 */

fun Disposable.addToCompositeDisposable(composite: CompositeDisposable) {
    composite.add(this)
}

fun String?.notEmpty(): Boolean =
    !TextUtils.isEmpty(this?.trim())
