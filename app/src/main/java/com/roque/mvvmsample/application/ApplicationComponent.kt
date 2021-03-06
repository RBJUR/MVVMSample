package com.roque.mvvmsample.application


import com.roque.mvvmsample.presentation.activities.GithubUsersComponent
import com.roque.mvvmsample.presentation.injection.ActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ServiceModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(app: MVVMSampleApplication)

    fun createActivityGithubUsers(module: ActivityModule): GithubUsersComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: MVVMSampleApplication): Builder

        fun build(): ApplicationComponent
    }
}