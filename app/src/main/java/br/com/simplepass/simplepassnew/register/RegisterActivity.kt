package br.com.simplepass.simplepassnew.register

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.simplepass.simplepassnew.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView {

    lateinit var mRegisterPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    override fun showProgress(show: Boolean) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToMainScreen() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRegisterError(error: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateForm(): Boolean {
        var result = true

        if(registerUsername.text.toString().length < 10 || loginUsername.text.toString().length > 21){
            //ToDo: feedback!
            result = false
        }

        if(registerPassword.text.toString().length < 6){
            //ToDo: feedback!
            result = false
        }

        if(registerName.text.toString().isEmpty()){
            //ToDo: feedback!
            result = false
        }

        if(registerEmail.text.toString().isEmpty()) {
            //ToDo: feedback!
            result = false
        }

        return result
    }

    override fun setPresenter(presenter: RegisterPresenter) {
        mRegisterPresenter = presenter
    }
}
