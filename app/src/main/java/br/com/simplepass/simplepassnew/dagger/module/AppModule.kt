package br.com.simplepass.simplepassnew.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by hinovamobile on 20/12/16.
 */
@Module
class AppModule(val mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplication() : Application {
        return mApplication
    }
}