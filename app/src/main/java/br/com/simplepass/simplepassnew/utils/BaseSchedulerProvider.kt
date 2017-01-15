package br.com.simplepass.simplepassnew.utils

import android.support.annotation.NonNull
import rx.Scheduler


/**
 * Created by leandro on 1/15/17.
 */
interface BaseSchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}