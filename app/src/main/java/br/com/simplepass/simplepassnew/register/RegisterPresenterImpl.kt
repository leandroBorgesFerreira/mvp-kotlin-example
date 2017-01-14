package br.com.simplepass.simplepassnew.register

import br.com.simplepass.simplepassnew.domain.User
import rx.Subscriber

/**
 * Created by leandro on 12/25/16.
 */
class RegisterPresenterImpl(val mRegisterView: RegisterView,
                            val mRegisterInteractor: RegisterInteractor) : RegisterPresenter {


    override fun tryRegister(user: User) {
        mRegisterInteractor.register(user).subscribe(object : Subscriber<User>(){
            override fun onNext(t: User?) {
                mRegisterInteractor.saveUser(user)
                onRegisterSuccess()
            }

            override fun onError(e: Throwable?) {
                onRegisterError(e?.message ?: "Erro ao conectar")
            }

            override fun onCompleted() {
            }
        })
    }

    override fun onRegisterError(error: String) {
        mRegisterView.showRegisterError(error)
    }

    override fun onRegisterSuccess() {
        mRegisterView.navigateToMainScreen()
    }
}