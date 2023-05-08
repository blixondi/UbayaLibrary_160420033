package com.shem.ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.shem.ubayalibrary.R
import com.shem.ubayalibrary.util.loadImage
import com.shem.ubayalibrary.viewmodel.ThesisDetailViewModel

class ThesisDetailFragment : Fragment() {
    private lateinit var viewModel: ThesisDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thesis_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var thesis_id = ""

        arguments?.let{
            thesis_id = ThesisDetailFragmentArgs.fromBundle(requireArguments()).thesisId
        }
        viewModel = ViewModelProvider(this).get(ThesisDetailViewModel::class.java)
        viewModel.fetch(thesis_id)

        val txtJudulThesis = view.findViewById<TextView>(R.id.txtJudulDetailTesis)
        val txtPenulisThesis = view.findViewById<TextView>(R.id.txtPenulisDetailTesis)
        val txtJenisPustaka = view.findViewById<TextView>(R.id.txtJenisPustaka)
        val txtTahunTesis = view.findViewById<TextView>(R.id.txtTahunTerbitTesis)
        val txtAbstrak = view.findViewById<TextView>(R.id.txtAbstrak)
        val imgDetailTesis = view.findViewById<ImageView>(R.id.imageView2)
        val progressBarImg = view.findViewById<ProgressBar>(R.id.progressImgTesis)
        val gambar = "https://digilib.ubaya.ac.id/img/nobook.png"

        viewModel.thesisLD.observe(viewLifecycleOwner) {thesis->
            txtJudulThesis.text = thesis.thesisTitle
            txtPenulisThesis.text = "Penulis: ${thesis.penulis}"
            txtJenisPustaka.text = "Jenis Pustaka: ${thesis.jenis_pustaka}"
            txtTahunTesis.text = "Tahun terbit: ${thesis.tahunTerbit}"
            txtAbstrak.text = thesis.abstrak
            imgDetailTesis.loadImage(gambar, progressBarImg)
        }
    }

}