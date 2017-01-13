package br.com.simplepass.simplepassnew.login

import br.com.simplepass.simplepassnew.domain.User
import retrofit2.Retrofit
import rx.Observable

/**
 * Created by leandro on 12/20/16.
 */
interface LoginInteractor {
    fun login(username: String, password: String) : Observable<User>
    fun setRetrofit(retrofit: Retrofit)
}