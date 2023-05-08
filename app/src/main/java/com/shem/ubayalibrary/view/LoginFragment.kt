@file:Suppress("KDocUnresolvedReference")

package com.shem.ubayalibrary.view

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.shem.ubayalibrary.R
import com.shem.ubayalibrary.viewmodel.BookDetailViewModel
import com.shem.ubayalibrary.viewmodel.LoginViewModel


class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    companion object{
        val currentid = "IDUSER"
        val currentuname = "CURRENTUNAME"
        val currentpass = "CURRENTPASS"
        val currentfname = "CURRENTFNAME"
        val currentlname = "CURRENTLNAME"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txtUsername = view.findViewById<TextInputEditText>(R.id.txtUsername)
        val txtPassword = view.findViewById<TextInputEditText>(R.id.txtPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val shared: SharedPreferences? = activity?.getSharedPreferences("UbayaLibrary",AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = shared?.edit()
        var currentFirstname = ""
        var currentLastname = ""
        var currentUsername = ""
        var currentUserid = ""
        var currentPass = ""
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val checkid = shared?.getString(LoginFragment.currentid, (-1).toString())
        if(checkid != "-1"){
            val action = LoginFragmentDirections.actionHomeFragment()
            Navigation.findNavController(view).navigate(action)
        }
        btnLogin.setOnClickListener {
            if(txtUsername.text.toString() != "" && txtPassword.text.toString() != ""){
                viewModel.login(txtUsername.text.toString(), txtPassword.text.toString())
                viewModel.userLD.observe(viewLifecycleOwner){user->
                    currentUserid = user.id.toString()
                    currentLastname = user.lastname.toString()
                    currentFirstname = user.firstname.toString()
                    currentUsername = user.username.toString()
                    currentPass = user.password.toString()
                    if(currentUserid == ""){
                        Toast.makeText(activity, "Wrong username/password", Toast.LENGTH_SHORT).show()
                    } else {
                        editor?.putString(currentid, currentUserid)
                        editor?.putString(currentpass, currentPass)
                        editor?.putString(currentfname, currentFirstname)
                        editor?.putString(currentlname, currentLastname)
                        editor?.putString(currentuname, currentUsername)
                        editor?.apply()
                        val action = LoginFragmentDirections.actionHomeFragment()
                        Navigation.findNavController(it).navigate(action)
                    }
                }
            } else{
                Toast.makeText(activity, "Wrong username/password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}