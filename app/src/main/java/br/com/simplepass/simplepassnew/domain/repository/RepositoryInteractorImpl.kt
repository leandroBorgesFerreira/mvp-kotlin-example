package br.com.simplepass.simplepassnew.domain.repository

import br.com.simplepass.simplepassnew.application.CustomApplication
import br.com.simplepass.simplepassnew.domain.User
import br.com.simplepass.simplepassnew.domain.VanInMap
import br.com.simplepass.simplepassnew.repository.ApiClient
import retrofit2.Retrofit
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by leandro on 1/15/17.
 */
class RepositoryInteractorImpl : RepositoryInteractor {
    @Inject
    lateinit var mRetrofit: Retrofit

    constructor(){
        CustomApplication.mNetComponent.inject(this)
    }

    override fun setRetrofit(retrofit: Retrofit){
        mRetrofit = retrofit
    }

    override fun login(username: String, password: String) =
            mRetrofit.create(ApiClient::class.java).login()

    override fun requestPoints(company: String): Observable<Iterable<VanInMap>> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun register(user: User): Observable<User> =
            mRetrofit.create(ApiClient::class.java).register(user)

}