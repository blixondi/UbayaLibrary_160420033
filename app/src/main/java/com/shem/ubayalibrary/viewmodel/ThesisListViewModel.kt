package com.shem.ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shem.ubayalibrary.model.Book
import com.shem.ubayalibrary.model.Thesis

class ThesisListViewModel(Application: Application): AndroidViewModel(Application) {
    val thesisLD = MutableLiveData<ArrayList<Thesis>>()
    val thesisLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG ="volleyTag"
    private var queue: RequestQueue?= null

    fun refresh(){
        loadingLD.value = true
        thesisLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://noinheim.my.id/ubayalib_api/get_thesis.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Thesis>>() { }.type
                val result = Gson().fromJson<ArrayList<Thesis>>(it, sType)
                thesisLD.value = result
                loadingLD.value = false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                thesisLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}