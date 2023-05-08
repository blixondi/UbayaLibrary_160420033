package com.shem.ubayalibrary.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

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
        val currentid = shared?.getString(LoginFragment.currentid, (-1).toString())
        val newPass = txtConfirmPass.text.toString()

        txtFirstname.setText(currentfname)
        txtLastname.setText(currentlname)
        txtUsername.setText(currentusername)

        btnChangePass.setOnClickListener {
            if(txtOldPass.text.toString() == currentpw){
                if(txtNewPass.text.toString() == txtConfirmPass.text.toString()){
                    if (currentid != null) {
                        viewModel.updatePass(newPass, currentid)
                        viewModel.statusLD.observe(viewLifecycleOwner){
                            if(it == "success"){
                                Toast.makeText(activity, "Successfully changed password!", Toast.LENGTH_SHORT).show()
                                editor?.remove("CURRENTPASS")
                                editor?.apply()
                                editor?.putString(LoginFragment.currentpass, newPass)
                                editor?.apply()
                            }
                        }
                    }
                } else{
                    Toast.makeText(activity, "New password did not match!", Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(activity, "Wrong password!", Toast.LENGTH_SHORT).show()
            }
        }

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