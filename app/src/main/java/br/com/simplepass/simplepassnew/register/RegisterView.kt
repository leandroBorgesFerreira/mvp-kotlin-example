package br.com.simplepass.simplepassnew.register

import br.com.simplepass.simplepassnew.base.BaseView

/**
 * Created by leandro on 12/22/16.
 */
interface RegisterView : BaseView<RegisterPresenter>{
    fun showProgress(show: Boolean)

    fun navigateToMainScreen()

    fun showRegisterError(error: String)

    fun validateForm() : Boolean
}