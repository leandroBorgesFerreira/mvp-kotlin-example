package br.com.simplepass.simplepassnew.utils

import rx.schedulers.Schedulers
import android.support.annotation.NonNull
import rx.Scheduler


/**
 * Created by leandro on 1/15/17.
 */
class ImmediateSchedulerProvider : BaseSchedulerProvider {

    override fun computation(): Scheduler = Schedulers.immediate()

    override fun io(): Scheduler = Schedulers.immediate()

    override fun ui(): Scheduler = Schedulers.immediate()

}