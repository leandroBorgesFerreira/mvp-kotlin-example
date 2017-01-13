package br.com.simplepass.simplepassnew.login

import br.com.simplepass.simplepassnew.domain.User
import rx.Subscriber

/**
 * Created by hinovamobile on 20/12/16.
 */
class LoginPresenterImpl(val mLoginView: LoginView, val mLoginInteractor: LoginInteractor) : LoginPresenter{

    override fun tryLogin(username: String, password: String) {
        mLoginView.showProgress(true)

        mLoginInteractor.login(username, password).subscribe(object : Subscriber<User>(){
            override fun onNext(t: User?) {

            }

            override fun onError(e: Throwable) {
                mLoginView.showProgress(false)
                onLoginError("Falha na comunicação")
//                    Log.d("On error called", "Error: " + e.message)
//
//                    e.printStackTrace()
//
//                    val erroMessage = e.message
//
//                    if(erroMessage != null) {
//                        listener.onRegisterError(erroMessage)
//                    }
            }

            override fun onCompleted() {
                mLoginView.showProgress(false)
                onLoginSuccess()
            }
        })

    }

    override fun onLoginError(error: String) {
        mLoginView.showLoginError(error)
    }

    override fun onLoginSuccess() {
        mLoginView.navigateToMainScreen()
    }
}