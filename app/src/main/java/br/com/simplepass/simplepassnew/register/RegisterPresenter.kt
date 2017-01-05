package br.com.simplepass.simplepassnew.register

import br.com.simplepass.simplepassnew.base.BasePresenter
import br.com.simplepass.simplepassnew.domain.User
import br.com.simplepass.simplepassnew.dto.RegisterDto

/**
 * Created by leandro on 12/22/16.
 */
interface RegisterPresenter : BasePresenter {
    fun tryRegister(user: User)

    fun onLoginError(error: String)

    fun onLoginSuccess()
}