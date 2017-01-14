package br.com.simplepass.simplepassnew.register

import br.com.simplepass.simplepassnew.BuildConfig
import br.com.simplepass.simplepassnew.TestUtils
import br.com.simplepass.simplepassnew.domain.User
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import rx.Observable
import rx.observers.TestSubscriber

/**
 * Created by leandro on 1/12/17.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, manifest = "src/main/AndroidManifest.xml", packageName = "br.com.simplepass.simplepassnew", sdk = intArrayOf(23))
class RegisterPresenterTest {

    @Mock
    lateinit var mRegisterInteractor: RegisterInteractor
    @Mock
    lateinit var mRegisterView: RegisterView

    lateinit var mRegisterPresenter: RegisterPresenter

    val erroMessage = "Erro"

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        `when`(mRegisterInteractor.register(provideDefaultUser())).thenReturn(
                Observable.just(provideDefaultUser()))

        `when`(mRegisterInteractor.register(provideDefaultUser2()))
                .thenReturn(Observable.create(Observable.OnSubscribe<User> {
                    sub -> sub.onError(Exception("Erro"))
                }))

        `when`(mRegisterInteractor.register(provideDefaultUser3()))
                .thenReturn(Observable.create(Observable.OnSubscribe<User> {
                    sub -> sub.onError(Exception())
                }))

        mRegisterPresenter = RegisterPresenterImpl(mRegisterView, mRegisterInteractor)
    }

    @Test
    fun registerTest(){
        mRegisterPresenter.tryRegister(provideDefaultUser())
        verify(mRegisterView).navigateToMainScreen()

        mRegisterPresenter.tryRegister(provideDefaultUser2())
        verify(mRegisterView).showRegisterError(erroMessage)

        mRegisterPresenter.tryRegister(provideDefaultUser3())
        verify(mRegisterView).showRegisterError("Erro ao conectar") //Mensagem de erro padrão

    }

    fun provideDefaultUser() = User(1, "5531991889992", "Leandro", null)
    fun provideDefaultUser2() = User(2, "5531991889992", "Leandro", null)
    fun provideDefaultUser3() = User(3, "5531991889992", "Leandro", null)
}