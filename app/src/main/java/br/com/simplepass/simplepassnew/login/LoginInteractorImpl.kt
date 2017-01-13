package br.com.simplepass.simplepassnew.login

import android.util.Log
import br.com.simplepass.simplepassnew.application.CustomApplication
import br.com.simplepass.simplepassnew.repository.ApiClient
import retrofit2.Retrofit
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by leandro on 12/20/16.
 */

class LoginInteractorImpl : LoginInteractor {
    @Inject
    lateinit var mRetrofit: Retrofit

    constructor(){
        CustomApplication.mNetComponent.inject(this)
    }

    override fun setRetrofit(retrofit: Retrofit){
        mRetrofit = retrofit
    }

    override fun login(username: String, password: String) =
        mRetrofit.create(ApiClient::class.java)
                .login()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
}