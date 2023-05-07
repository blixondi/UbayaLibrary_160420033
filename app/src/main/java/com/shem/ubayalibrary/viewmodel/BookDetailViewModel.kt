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
import com.shem.ubayalibrary.model.Book

class BookDetailViewModel (Application: Application): AndroidViewModel(Application){
    val bookLD = MutableLiveData<Book>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(book_id:String) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://noinheim.my.id/ubayalib_api/get_books.php?id=$book_id"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<Book>(it, Book::class.java)
                bookLD.value = result

                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley", it.toString())

            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}