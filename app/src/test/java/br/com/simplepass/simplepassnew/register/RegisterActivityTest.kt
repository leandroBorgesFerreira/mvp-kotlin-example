package br.com.simplepass.simplepassnew.register

import android.support.design.widget.TextInputEditText
import android.widget.Button
import br.com.simplepass.simplepassnew.BuildConfig
import br.com.simplepass.simplepassnew.R
import br.com.simplepass.simplepassnew.login.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowActivity
import org.robolectric.shadows.ShadowToast

/**
 * Created by leandro on 12/25/16.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, manifest = "src/main/AndroidManifest.xml", packageName = "br.com.simplepass.simplepassnew", sdk = intArrayOf(23))
class RegisterActivityTest {
    lateinit var mRegisterView: RegisterView
    lateinit var mShadowActivity: ShadowActivity


    @Before
    fun setup() {
        mRegisterView = Robolectric.setupActivity(RegisterActivity::class.java)
        mShadowActivity = Shadows.shadowOf(mRegisterView as RegisterActivity)
    }

    @Test
    fun formTest(){
        val activity = mRegisterView as RegisterActivity

        //Correct form
        setDefaultValuesInForm(activity)
        Assert.assertTrue(activity.validateForm())

        //Phone too short
        setDefaultValuesInForm(activity)
        activity.registerUsername.setText("1")
        Assert.assertFalse(activity.validateForm())

        //Phone too long
        setDefaultValuesInForm(activity)
        activity.registerUsername.setText("1222383383738282828292827")
        Assert.assertFalse(activity.validateForm())

        //Password too short
        setDefaultValuesInForm(activity)
        activity.registerPassword.setText("1")
        Assert.assertFalse(activity.validateForm())

        //Is not a email
        setDefaultValuesInForm(activity)
        activity.registerEmail.setText("1sdf")
        Assert.assertFalse(activity.validateForm())
    }

    @Test
    fun navigateToMainScreen(){
        mRegisterView.navigateToMainScreen()
    }

    @Test
    fun errorMessageTest(){
        val errorMsg = "Ops!"

        mRegisterView.showRegisterError(errorMsg)

        Assert.assertEquals(errorMsg, ShadowToast.getTextOfLatestToast())
    }

    fun setDefaultValuesInForm(registerActivity: RegisterActivity){
        registerActivity.registerUsername.setText("5531991787878")
        registerActivity.registerPassword.setText("123456")
        registerActivity.registerName.setText("Leandro")
        registerActivity.registerEmail.setText("unittest@gmail.com")
    }

}