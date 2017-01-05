package br.com.simplepass.simplepassnew.login

import android.app.ActivityOptions
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import br.com.simplepass.simplepassnew.R
import br.com.simplepass.simplepassnew.base.BasePresenter
import br.com.simplepass.simplepassnew.map.MainActivity
import br.com.simplepass.simplepassnew.register.RegisterActivity
import br.com.simplepass.simplepassnew.resetPassword.ResetPasswordActivity
import dagger.Module

import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.onClick

class LoginActivity : AppCompatActivity(), LoginView{

    private lateinit var mLoginPresenter : LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginPresenter = LoginPresenterImpl(this, LoginInteractorImpl())

        loginUsername.addTextChangedListener(PhoneNumberFormattingTextWatcher())


        loginBtnRegister.onClick { navigateToRegister() }
        loginBtnResetPassword.onClick { navigateToResetPassword() }
        loginBtnEnter.onClick {
            mLoginPresenter.tryLogin(loginUsername.text.toString(), loginPassword.text.toString())
        }
    }

    override fun setPresenter(presenter: LoginPresenter) {
        mLoginPresenter = presenter
    }

    override fun showProgress(show: Boolean) {
        if(show){
            loginBtnEnter.startAnimation()
        } else{
            loginBtnEnter.revertAnimation()
        }
    }

    override fun navigateToRegister() {
        val intent = intentFor<RegisterActivity>()

        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP) {
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this)

            startActivity(intent, activityOptions.toBundle())
        } else{
            startActivity(intent)
        }
    }

    override fun navigateToResetPassword() {
        val intent = intentFor<ResetPasswordActivity>()

        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP) {
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this)

            startActivity(intent, activityOptions.toBundle())
        } else{
            startActivity(intent)
        }
    }

    override fun navigateToMainScreen() {
        val intent = intentFor<MainActivity>()

        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP) {
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this)

            startActivity(intent, activityOptions.toBundle())
        } else{
            startActivity(intent)
        }
    }

    override fun showLoginError(error: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateCredentials() : Boolean {
        var result = true

        if(loginUsername.text.toString().length < 10 || loginUsername.text.toString().length > 21){
            //ToDo: feedback!
            result = false
        }

        if(loginPassword.text.toString().length < 6){
            //ToDo: feedback!
            result = false
        }

        return result;
    }
}
