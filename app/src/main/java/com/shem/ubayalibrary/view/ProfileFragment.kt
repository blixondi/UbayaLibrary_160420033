package com.shem.ubayalibrary.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.shem.ubayalibrary.R
import com.shem.ubayalibrary.viewmodel.LoginViewModel


class ProfileFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shared: SharedPreferences? = activity?.getSharedPreferences("UbayaLibrary",
            AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = shared?.edit()
        val txtFirstname = view.findViewById<EditText>(R.id.txtEditFirstname)
        val txtLastname = view.findViewById<EditText>(R.id.txtEditLastname)
        val txtUsername = view.findViewById<EditText>(R.id.txtEditUsername)
        val txtOldPass = view.findViewById<EditText>(R.id.txtEditOldPassword)
        val txtNewPass = view.findViewById<EditText>(R.id.txtEditNewPassword)
        val txtConfirmPass = view.findViewById<EditText>(R.id.txtConfirmPass)
        val btnChangePass = view.findViewById<Button>(R.id.btnChangePass)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        val currentfname = shared?.getString(LoginFragment.currentfname, (-1).toString())
        val currentlname = shared?.getString(LoginFragment.currentlname, (-1).toString())
        val currentusername = shared?.getString(LoginFragment.currentuname, (-1).toString())
        val currentpw = shared?.getString(LoginFragment.currentpass, (-1).toString())

        txtFirstname.setText(currentfname)
        txtLastname.setText(currentlname)
        txtUsername.setText(currentusername)

        btnLogout.setOnClickListener {
            editor?.remove("IDUSER")
            editor?.remove("CURRENTUNAME")
            editor?.remove("CURRENTPASS")
            editor?.remove("CURRENTFNAME")
            editor?.remove("CURRENTLNAME")
            editor?.apply()
            val action = ProfileFragmentDirections.actionLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}