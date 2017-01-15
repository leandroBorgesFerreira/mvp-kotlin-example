package br.com.simplepass.simplepassnew.login

import android.widget.Button
import br.com.simplepass.simplepassnew.BuildConfig
import br.com.simplepass.simplepassnew.domain.User
import br.com.simplepass.simplepassnew.domain.repository.RepositoryInteractor
import br.com.simplepass.simplepassnew.utils.ImmediateSchedulerProvider
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.observers.TestSubscriber


/**
 * Created by leandro on 12/24/16.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, manifest = "src/main/AndroidManifest.xml", packageName = "br.com.simplepass.simplepassnew", sdk = intArrayOf(23))
class LoginPresenterImplTest {

    @Mock
    lateinit var mRepositoryInteractor: RepositoryInteractor
    @Mock
    lateinit var mLoginView: LoginView

    lateinit var mLoginPresenter: LoginPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        `when`(mRepositoryInteractor.login("a", "a")).thenReturn(Observable.just(User(1,
                "31991889992",
                "Leandro",
                null,
                "email@email.com")))

        `when`(mRepositoryInteractor.login("b", "b")).thenReturn(Observable.create(Observable.OnSubscribe<User> {
            sub -> sub.onError(Exception("Erro"))
        }))

        mLoginPresenter = LoginPresenterImpl(
                mLoginView, mRepositoryInteractor, ImmediateSchedulerProvider())
    }

    @Test
    fun loginTest(){
        mLoginPresenter.tryLogin("a", "a")
        Mockito.verify(mLoginView).navigateToMainScreen()

        mLoginPresenter.tryLogin("b", "b")
        Mockito.verify(mLoginView).showLoginError("Erro")
    }

}