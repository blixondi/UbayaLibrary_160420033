@file:Suppress("KDocUnresolvedReference")

package com.shem.ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.shem.ubayalibrary.R




class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val txtUsername = view.findViewById<TextInputEditText>(R.id.txtUsername)
//        val txtPassword = view.findViewById<TextInputEditText>(R.id.txtPassword)
//        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
//        val btnRegister = view.findViewById<Button>(R.id.btnRegister)
//        btnRegister.setOnClickListener {
//            val action = LoginFragmentDirections.actionRegisterFragment()
//            Navigation.findNavController(it).navigate(action)
//        }
//        btnLogin.setOnClickListener {
//            val action = LoginFragmentDirections.actionHomeFragment()
//            Navigation.findNavController(it).navigate(action)
//        }
//    }
}