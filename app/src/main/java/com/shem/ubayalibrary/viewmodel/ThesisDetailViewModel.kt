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
import com.shem.ubayalibrary.model.Thesis

class ThesisDetailViewModel (Application: Application): AndroidViewModel(Application) {
    val thesisLD = MutableLiveData<Thesis>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(thesis_id:String) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://noinheim.my.id/ubayalib_api/get_thesis.php?id=$thesis_id"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<Thesis>(it, Thesis::class.java)
                thesisLD.value = result
                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}