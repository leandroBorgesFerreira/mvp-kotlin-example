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
import org.junit.Assert
import org.robolectric.shadows.ShadowToast


/**
 * Created by leandro on 12/24/16.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, manifest = "src/main/AndroidManifest.xml", packageName = "br.com.simplepass.simplepassnew", sdk = intArrayOf(23))
class LoginActivityTest {

    lateinit var activity: LoginActivity
    lateinit var shadowActivity: ShadowActivity
    lateinit var username: TextInputEditText
    lateinit var passwordET: TextInputEditText
    lateinit var btnLogin: Button
    lateinit var btnRecoverPassword: Button
    lateinit var btnRegister: Button

    @Before
    fun setup() {
        activity = Robolectric.setupActivity(LoginActivity::class.java)
        shadowActivity = Shadows.shadowOf(activity)
        username = activity.findViewById(R.id.loginUsername) as TextInputEditText
        passwordET = activity.findViewById(R.id.loginPassword) as TextInputEditText
        btnLogin = activity.findViewById(R.id.loginBtnEnter) as Button
        btnRecoverPassword = activity.findViewById(R.id.loginBtnResetPassword) as Button
        btnRegister = activity.findViewById(R.id.loginBtnRegister) as Button
    }

    @Test
    fun formTest(){
        //Phone too short
        username.setText("1")
        passwordET.setText("123456")
        Assert.assertFalse(activity.validateCredentials())

        //Phone too long
        username.setText("1222383383738282828292827")
        passwordET.setText("123456")
        Assert.assertFalse(activity.validateCredentials())

        //Password too short
        username.setText("31991889992")
        passwordET.setText("1")
        Assert.assertFalse(activity.validateCredentials())

        //Correct form
        username.setText("31991889992")
        passwordET.setText("1234567")
        Assert.assertTrue(activity.validateCredentials())
    }

    @Test
    fun navigateToRegisterTest(){
        btnRegister.performClick()

        val intent = shadowActivity.nextStartedActivity
        Assert.assertEquals(intent.toString(), Intent(activity,
                RegisterActivity::class.java).toString())
    }

    @Test
    fun navigateToResetPasswordTest(){
        btnRecoverPassword.performClick()

        val intent = shadowActivity.nextStartedActivity
        Assert.assertEquals(intent.toString(), Intent(activity,
                ResetPasswordActivity::class.java).toString())
    }

    @Test
    fun errorMessageTest(){
        val errorMsg = "Ops!"

        activity.showLoginError(errorMsg)

        Assert.assertEquals(errorMsg, ShadowToast.getTextOfLatestToast())
    }

}