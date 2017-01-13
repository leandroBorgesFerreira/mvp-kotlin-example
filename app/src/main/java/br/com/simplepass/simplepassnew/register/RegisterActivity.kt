package br.com.simplepass.simplepassnew.register

import android.app.ActivityOptions
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.simplepass.simplepassnew.R
import br.com.simplepass.simplepassnew.map.MainActivity

import kotlinx.android.synthetic.main.activity_register.*

import org.jetbrains.anko.intentFor

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
        val intent = intentFor<MainActivity>()

        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP) {
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this)

            startActivity(intent, activityOptions.toBundle())
        } else{
            startActivity(intent)
        }
    }

    override fun showRegisterError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun validateForm(): Boolean {
        var result = true

        if(registerUsername.text.toString().length < 10 || registerUsername.text.toString().length > 21){
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

        if(registerEmail.text.toString().isEmpty() || !registerEmail.text.toString().contains("@")) {
            //ToDo: feedback!
            result = false
        }

        return result
    }

    override fun setPresenter(presenter: RegisterPresenter) {
        mRegisterPresenter = presenter
    }
}
