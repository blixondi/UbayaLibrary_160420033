package com.shem.ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.shem.ubayalibrary.model.Book
import com.shem.ubayalibrary.model.User
import org.json.JSONObject

class LoginViewModel (Application: Application): AndroidViewModel(Application){
    val userLD = MutableLiveData<User>()
    val statusLD = MutableLiveData<String>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(username: String, password: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://noinheim.my.id/ubayalib_api/login.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                val result = Gson().fromJson<User>(it, User::class.java)
                userLD.value = result
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map.set("username", username)
                map.set("password", password)
                return map
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun updatePass(password: String, id: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://noinheim.my.id/ubayalib_api/update_pass.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                val obj = JSONObject(it)
                val result = obj.getString("result")
                statusLD.value = result
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map.set("password", password)
                map.set("id", id)
                return map
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

}
