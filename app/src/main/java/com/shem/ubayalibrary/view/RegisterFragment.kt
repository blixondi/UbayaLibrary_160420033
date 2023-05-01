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

class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstName = view.findViewById<TextInputEditText>(R.id.txtFirstName)
        val lastname = view.findViewById<TextInputEditText>(R.id.txtLastName)
        val newUser = view.findViewById<TextInputEditText>(R.id.txtNewUser)
        val newPass = view.findViewById<TextInputEditText>(R.id.txtNewPassword)
        val confPass = view.findViewById<TextInputEditText>(R.id.txtConfirmPass)
        val btnCreate = view.findViewById<Button>(R.id.btnCreateAcc)
        btnCreate.setOnClickListener {
            val action = RegisterFragmentDirections.actionLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}