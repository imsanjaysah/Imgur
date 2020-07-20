/*
 * RunOn.kt
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.injection.annotations

import com.sanjay.imgur.schedulers.SchedulerType
import javax.inject.Qualifier

/**
 * Qualifier to define Scheduler type (io, computation, or ui main thread).
 * @author Sanjay.Sah
 */
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class RunOn(val value: SchedulerType = SchedulerType.IO)