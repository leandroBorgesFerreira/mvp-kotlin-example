package br.com.simplepass.simplepassnew.register

import br.com.simplepass.simplepassnew.domain.User
import rx.Observable

/**
 * Created by leandro on 12/22/16.
 */
interface RegisterInteractor {
    fun register(user: User) : Observable<User>
    fun saveUser(user: User)
}