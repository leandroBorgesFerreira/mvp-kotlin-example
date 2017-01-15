package br.com.simplepass.simplepassnew.dagger.component

import br.com.simplepass.simplepassnew.dagger.module.AppModule
import br.com.simplepass.simplepassnew.dagger.module.NetModule
import br.com.simplepass.simplepassnew.domain.repository.RepositoryInteractorImpl
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by hinovamobile on 20/12/16.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface NetComponent {
    fun inject(interactor: RepositoryInteractorImpl)
    fun setRetrofit(retrofit: Retrofit)
}