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
import com.shem.ubayalibrary.model.History

class HistoryViewModel(Application: Application): AndroidViewModel(Application) {
    val historysLD = MutableLiveData<ArrayList<History>>()
    val historyLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG ="volleyTag"
    private var queue: RequestQueue ?= null

    fun refresh(id: String){
        loadingLD.value = true
        historyLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://noinheim.my.id/ubayalib_api/get_history.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                val sType = object : TypeToken<ArrayList<History>>() { }.type
                val result = Gson().fromJson<ArrayList<History>>(it, sType)
                historysLD.value = result
                loadingLD.value = false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                historyLoadErrorLD.value = false
                loadingLD.value = false
            }){
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map.set("id", id)
                return map
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}