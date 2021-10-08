package com.example.rxretrofitmvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.rxretrofitmvvm.model.MovieSearch
import com.example.rxretrofitmvvm.network.RetrofitInstance
import com.example.rxretrofitmvvm.network.RetrofitService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityViewModel: ViewModel() {

    var list: MutableLiveData<MovieSearch> = MutableLiveData()

    fun getMovieSearchObservable(): MutableLiveData<MovieSearch>{
        return list
    }

    fun getMovieSearch(api: String, text: String){
        val retrofitInstance = RetrofitInstance.getRetrofit().create(RetrofitService::class.java)
        retrofitInstance.getMovieSearch(api, text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getMovieSearchObserver())
    }

    private fun getMovieSearchObserver(): io.reactivex.rxjava3.core.Observer<MovieSearch> {
        return object : io.reactivex.rxjava3.core.Observer<MovieSearch>{
            override fun onSubscribe(d: Disposable?) {
                //hide progress
            }

            override fun onNext(t: MovieSearch?) {
                list.postValue(t)
            }

            override fun onError(e: Throwable?) {
                list.postValue(null)
            }

            override fun onComplete() {
                //show progress
            }

        }
    }


}