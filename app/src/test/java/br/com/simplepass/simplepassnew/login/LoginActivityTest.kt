package br.com.simplepass.simplepassnew.login

import android.widget.Button
import android.widget.EditText
import br.com.simplepass.simplepassnew.BuildConfig
import br.com.simplepass.simplepassnew.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowActivity
import android.content.Intent
import android.support.design.widget.TextInputEditText
import br.com.simplepass.simplepassnew.register.RegisterActivity
import br.com.simplepass.simplepassnew.resetPassword.ResetPasswordActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import org.junit.Assert
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.shadows.ShadowToast


/**
 * Created by leandro on 12/24/16.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, manifest = "src/main/AndroidManifest.xml", packageName = "br.com.simplepass.simplepassnew", sdk = intArrayOf(23))
class LoginActivityTest {

    lateinit var mLoginActivity: LoginActivity
    lateinit var shadowActivity: ShadowActivity
    lateinit var usernameET: TextInputEditText
    lateinit var passwordET: TextInputEditText
    lateinit var btnLogin: Button
    lateinit var btnRecoverPassword: Button
    lateinit var btnRegister: Button

    @Mock
    lateinit var mPresenter: LoginPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        mLoginActivity = Robolectric.setupActivity(LoginActivity::class.java)
        mLoginActivity.setPresenter(mPresenter)

        shadowActivity = Shadows.shadowOf(mLoginActivity)
        usernameET = mLoginActivity.findViewById(R.id.loginUsername) as TextInputEditText
        passwordET = mLoginActivity.findViewById(R.id.loginPassword) as TextInputEditText
        btnLogin = mLoginActivity.findViewById(R.id.loginBtnEnter) as Button
        btnRecoverPassword = mLoginActivity.findViewById(R.id.loginBtnResetPassword) as Button
        btnRegister = mLoginActivity.findViewById(R.id.loginBtnRegister) as Button
    }

    @Test
    fun formTest(){
        //Phone too short
        usernameET.setText("1")
        passwordET.setText("123456")
        Assert.assertFalse(mLoginActivity.validateCredentials())

        //Phone too long
        usernameET.setText("1222383383738282828292827")
        passwordET.setText("123456")
        Assert.assertFalse(mLoginActivity.validateCredentials())

        //Password too short
        usernameET.setText("31991889992")
        passwordET.setText("1")
        Assert.assertFalse(mLoginActivity.validateCredentials())

        //Correct form
        usernameET.setText("31991889992")
        passwordET.setText("1234567")
        Assert.assertTrue(mLoginActivity.validateCredentials())
    }

    @Test
    fun presenterCallTest(){
        val username = "31991889992"
        val password = "1234567"

        usernameET.setText(username)
        passwordET.setText(password)

        mLoginActivity.loginBtnEnter.performClick()

        verify(mPresenter).tryLogin(username, password)
    }

    @Test
    fun navigateToRegisterTest(){
        btnRegister.performClick()

        val intent = shadowActivity.nextStartedActivity
        Assert.assertEquals(intent.toString(), Intent(mLoginActivity,
                RegisterActivity::class.java).toString())
    }

    @Test
    fun navigateToResetPasswordTest(){
        btnRecoverPassword.performClick()

        val intent = shadowActivity.nextStartedActivity
        Assert.assertEquals(intent.toString(), Intent(mLoginActivity,
                ResetPasswordActivity::class.java).toString())
    }

    @Test
    fun errorMessageTest(){
        val errorMsg = "Ops!"

        mLoginActivity.showLoginError(errorMsg)

        Assert.assertEquals(errorMsg, ShadowToast.getTextOfLatestToast())
    }

}