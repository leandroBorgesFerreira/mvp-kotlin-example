package br.com.simplepass.simplepassnew.register

import br.com.simplepass.simplepassnew.domain.User
import br.com.simplepass.simplepassnew.domain.repository.RepositoryInteractor
import br.com.simplepass.simplepassnew.utils.BaseSchedulerProvider
import rx.Subscriber

/**
 * Created by leandro on 12/25/16.
 */
class RegisterPresenterImpl(val mRegisterView: RegisterView,
                            val mRepositoryInteractor: RepositoryInteractor,
                            val mSchedulerProvider: BaseSchedulerProvider) : RegisterPresenter {

    override fun tryRegister(user: User) {
        mRepositoryInteractor.register(user)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(object : Subscriber<User>(){
            override fun onNext(t: User?) {
                //ToDo: Save the user here!
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