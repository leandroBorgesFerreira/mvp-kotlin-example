package br.com.simplepass.simplepassnew.dagger.component

import br.com.simplepass.simplepassnew.dagger.module.AppModule
import br.com.simplepass.simplepassnew.dagger.module.NetModule
import br.com.simplepass.simplepassnew.login.LoginInteractorImpl
import br.com.simplepass.simplepassnew.register.RegisterInteractorImpl
import dagger.Component
import javax.inject.Singleton

/**
 * Created by hinovamobile on 20/12/16.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface NetComponent {
    fun inject(interactor: LoginInteractorImpl)
    fun inject(interactor: RegisterInteractorImpl)
}