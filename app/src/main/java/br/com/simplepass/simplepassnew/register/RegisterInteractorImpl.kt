package br.com.simplepass.simplepassnew.register

import br.com.simplepass.simplepassnew.application.CustomApplication
import br.com.simplepass.simplepassnew.domain.User
import br.com.simplepass.simplepassnew.repository.ApiClient
import retrofit2.Retrofit
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by leandro on 12/25/16.
 */
class RegisterInteractorImpl : RegisterInteractor{

    @Inject
    lateinit var mRetrofit: Retrofit

    constructor(){
        CustomApplication.mNetComponent.inject(this)
    }

    override fun register(user: User): Observable<User> {
        return mRetrofit.create(ApiClient::class.java)
                .register(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveUser(user: User) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}