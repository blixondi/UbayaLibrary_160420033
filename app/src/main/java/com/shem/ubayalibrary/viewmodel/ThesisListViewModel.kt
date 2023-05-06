package com.shem.ubayalibrary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.shem.ubayalibrary.model.Thesis

class ThesisListViewModel(Application: Application): AndroidViewModel(Application) {
    val booksLD = MutableLiveData<ArrayList<Thesis>>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG ="volleyTag"
    private var queue: RequestQueue?= null

    fun refresh(){

    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}