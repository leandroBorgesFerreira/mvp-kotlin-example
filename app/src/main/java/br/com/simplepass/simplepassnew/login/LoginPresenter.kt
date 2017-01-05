package br.com.simplepass.simplepassnew.login

import br.com.simplepass.simplepassnew.base.BasePresenter

/**
 * Created by leandro on 12/20/16.
 */
interface LoginPresenter : BasePresenter {
    fun tryLogin(username: String, password: String)

    fun onLoginError(error: String)

    fun onLoginSuccess()
}