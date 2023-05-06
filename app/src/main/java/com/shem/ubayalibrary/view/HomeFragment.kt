package com.shem.ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.shem.ubayalibrary.R


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnBrowseBook = view.findViewById<Button>(R.id.btnBrowseBook)
        val btnBrowseThesis = view.findViewById<Button>(R.id.btnBrowseThesis)
        btnBrowseBook.setOnClickListener {
            val action = HomeFragmentDirections.actionBookFragment()
            Navigation.findNavController(it).navigate(action)
        }
        btnBrowseThesis.setOnClickListener {
            val action = HomeFragmentDirections.actionThesisFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}