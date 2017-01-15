package br.com.simplepass.simplepassnew.register

import android.support.design.widget.TextInputEditText
import android.widget.Button
import br.com.simplepass.simplepassnew.BuildConfig
import br.com.simplepass.simplepassnew.R
import br.com.simplepass.simplepassnew.TestUtils
import br.com.simplepass.simplepassnew.domain.User
import br.com.simplepass.simplepassnew.login.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
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

    @Mock
    lateinit var mPresenter: RegisterPresenter

    val defaultUsername = "31991889992"
    val defaultPassword = "123456"
    val defaultName = "Leandro"
    val defaultEmail = "unittest@gmail.com"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);

        mRegisterView = Robolectric.setupActivity(RegisterActivity::class.java)
        mRegisterView.setPresenter(mPresenter)

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

    @Test
    fun presenterCallTest(){
        val activity = mRegisterView as RegisterActivity

        setDefaultValuesInForm(activity)
        activity.registerBtnEnter.performClick()

        verify(mPresenter).tryRegister(User(0, defaultUsername, defaultPassword, defaultName, defaultEmail))
    }

    fun setDefaultValuesInForm(registerActivity: RegisterActivity){
        registerActivity.registerUsername.setText(defaultUsername)
        registerActivity.registerPassword.setText(defaultPassword)
        registerActivity.registerName.setText(defaultName)
        registerActivity.registerEmail.setText(defaultEmail)
    }

}