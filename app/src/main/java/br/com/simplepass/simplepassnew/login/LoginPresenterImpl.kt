package br.com.simplepass.simplepassnew.login

import br.com.simplepass.simplepassnew.domain.User
import br.com.simplepass.simplepassnew.domain.repository.RepositoryInteractor
import br.com.simplepass.simplepassnew.utils.BaseSchedulerProvider
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by hinovamobile on 20/12/16.
 */
class LoginPresenterImpl(val mLoginView: LoginView,
                         val mRepositoryInteractor: RepositoryInteractor,
                         val mSchedulerProvider: BaseSchedulerProvider) : LoginPresenter{

    override fun tryLogin(username: String, password: String) {
        mLoginView.showProgress(true)

        mRepositoryInteractor.login(username, password)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(object : Subscriber<User>(){
            override fun onNext(t: User?) {

            }

            override fun onError(e: Throwable) {
                mLoginView.showProgress(false)
                onLoginError(e.message ?: "Erro na comunicacao")
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