package br.com.simplepass.simplepassnew.login

import br.com.simplepass.simplepassnew.BuildConfig
import br.com.simplepass.simplepassnew.domain.User
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.observers.TestSubscriber

/**
 * Created by leandro on 12/24/16.
 *
 * This test is not completed... MockWebServer simply doesnt work =/
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
        mServer.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(Gson().toJson(User(1, "991889992", "Leandro", "123"))))

        mLoginInteractor.setRetrofit(Retrofit.Builder()
                .baseUrl(mServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build())

        val testSubscriber = TestSubscriber.create<User>()

        mLoginInteractor.login("31991889992", "Leandro").subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
//        testSubscriber.assertCompleted()
    }

    @After
    fun tearDown(){
        mServer.shutdown()
    }



}