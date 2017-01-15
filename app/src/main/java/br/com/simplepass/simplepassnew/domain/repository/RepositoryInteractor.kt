package br.com.simplepass.simplepassnew.domain.repository

import br.com.simplepass.simplepassnew.domain.User
import br.com.simplepass.simplepassnew.domain.VanInMap
import retrofit2.Retrofit
import rx.Observable

/**
 * Created by leandro on 1/15/17.
 */
interface RepositoryInteractor {
    fun login(username: String, password: String) : Observable<User>
    fun requestPoints(company: String): Observable<Iterable<VanInMap>>
    fun register(user: User) : Observable<User>
    fun setRetrofit(retrofit: Retrofit)
}