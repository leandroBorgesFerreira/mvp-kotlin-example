package br.com.simplepass.simplepassnew.login

import br.com.simplepass.simplepassnew.base.BasePresenter
import br.com.simplepass.simplepassnew.base.BaseView

/**
 * Created by leandro on 12/20/16.
 */
interface LoginView : BaseView<LoginPresenter> {
    fun showProgress(show: Boolean)

    fun navigateToRegister()

    fun navigateToResetPassword()

    fun navigateToMainScreen()

    fun showLoginError(error: String)

    fun validateCredentials() : Boolean

    override fun setPresenter(presenter: LoginPresenter)
}