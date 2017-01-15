package br.com.simplepass.simplepassnew.utils

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by leandro on 1/15/17.
 */
class SchedulerProvider private constructor(): BaseSchedulerProvider{

    private object Holder { val INSTANCE = SchedulerProvider()}

    companion object{
        val instance: SchedulerProvider by lazy { Holder.INSTANCE }
    }

    override fun computation(): Scheduler = Schedulers.computation()


    override fun io(): Scheduler = Schedulers.io()


    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

}