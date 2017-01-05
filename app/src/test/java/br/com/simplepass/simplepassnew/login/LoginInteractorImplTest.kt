package br.com.simplepass.simplepassnew.login

import br.com.simplepass.simplepassnew.BuildConfig
import br.com.simplepass.simplepassnew.domain.User
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import rx.observers.TestSubscriber

/**
 * Created by leandro on 12/24/16.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, manifest = "src/main/AndroidManifest.xml", packageName = "br.com.simplepass.simplepassnew", sdk = intArrayOf(23))
class LoginInteractorImplTest {

    lateinit var mLoginInteractor : LoginInteractor
    lateinit var mServer: MockWebServer


    @Before
    fun setUp(){
        mLoginInteractor = LoginInteractorImpl()
        mServer = MockWebServer()
        mServer.start()
    }

    @Test
    fun loginTest(){
        mServer.url("http://192.168.0.10:8080/login")

        val testSubscriber = TestSubscriber.create<User>()

        mLoginInteractor.login("31991889992", "lala").subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
//        testSubscriber.assertCompleted()
    }

    @After
    fun tearDown(){
        mServer.shutdown()
    }



}