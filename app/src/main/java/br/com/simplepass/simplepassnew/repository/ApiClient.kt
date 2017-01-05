package br.com.simplepass.simplepassnew.repository

import br.com.simplepass.simplepassnew.domain.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

/**
 * Created by leandro on 12/20/16.
 */
interface ApiClient {
    @GET("login")
    fun login() : Observable<User>

    @POST("register")
    fun register(@Body user: User) : Observable<User>
}