package com.roque.mvvmsample.presentation

import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.roque.mvvmsample.data.GithubUser
import com.roque.mvvmsample.data.RetrofitConfig.Companion.createApi
import com.roque.mvvmsample.data.Service
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class GithubUsersViewModel(private val service: Service) : ViewModel() {

    @NonNull
    private val compositeDisposable = CompositeDisposable()

    val users = MutableLiveData<List<GithubUser>>()
    val error = MutableLiveData<Throwable>()

    init {
        compositeDisposable.add(fetchUsers())
    }

    fun  fetchUsers() : Disposable {
        return service.getGithubUsers()
                .subscribe(::updateUsers, ::displayError)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun updateUsers(list: List<GithubUser>){
        users.postValue(list)
    }

    private fun displayError(t:Throwable){
        error.postValue(t)
    }

    class GithubUsersViewModelFactory(private val baseUrl: String): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel>create(modelClass: Class<T>) = GithubUsersViewModel(createApi(baseUrl)) as T
    }
}

