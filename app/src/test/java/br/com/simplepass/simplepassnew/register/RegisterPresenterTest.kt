package br.com.simplepass.simplepassnew.register

import br.com.simplepass.simplepassnew.BuildConfig
import br.com.simplepass.simplepassnew.TestUtils
import br.com.simplepass.simplepassnew.domain.User
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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

    lateinit var mUser : User

    @Before
    fun setup(){
        mUser = provideDefaultUser()

        MockitoAnnotations.initMocks(this)
        Mockito.`when`(mRegisterInteractor.register(provideDefaultUser())).thenReturn(
                Observable.just(provideDefaultUser()))

    }

    @Test
    fun registerTest(){
        val testSubscriber = TestSubscriber.create<User>()

        mRegisterInteractor.register(provideDefaultUser()).subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertCompleted()
    }

    fun provideDefaultUser() = User(1, "5531991889992", "Leandro", null)
}