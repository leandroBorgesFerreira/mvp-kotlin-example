package br.com.simplepass.simplepassnew.application

import android.app.Application
import br.com.simplepass.simplepassnew.R
import br.com.simplepass.simplepassnew.dagger.component.DaggerNetComponent
import br.com.simplepass.simplepassnew.dagger.component.NetComponent
import br.com.simplepass.simplepassnew.dagger.module.AppModule
import br.com.simplepass.simplepassnew.dagger.module.NetModule

/**
 * Application costumizado. Feito para ser utilizado o Realm
 */
class CustomApplication : Application() {

    companion object {
        lateinit var mNetComponent: NetComponent
    }

    override fun onCreate() {
        super.onCreate()

        //AndroidThreeTen.init(this)

        mNetComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(getString(R.string.api_base_url)))
                .build()
    }
}
