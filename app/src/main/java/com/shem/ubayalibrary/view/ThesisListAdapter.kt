package com.shem.ubayalibrary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.shem.ubayalibrary.R
import com.shem.ubayalibrary.model.Thesis
import com.shem.ubayalibrary.util.loadImage

class ThesisListAdapter(val thesisList: ArrayList<Thesis>):RecyclerView.Adapter<ThesisListAdapter.ThesisViewHolder>() {
    class ThesisViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThesisViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.thesis_item, parent, false)

        return ThesisViewHolder(view)
    }

    override fun getItemCount(): Int {
        return thesisList.size
    }

    override fun onBindViewHolder(holder: ThesisViewHolder, position: Int) {
        val txtJudulThesis = holder.view.findViewById<TextView>(R.id.txtJudulTesis)
        val txtPenulisTesis = holder.view.findViewById<TextView>(R.id.txtPenulisTesis)
        val txtTahunThesis = holder.view.findViewById<TextView>(R.id.txtTahunTesis)
        val btnDetailTesis = holder.view.findViewById<TextView>(R.id.btnDetailTesis)
        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarImgThesis)
        val imgThesis = holder.view.findViewById<ImageView>(R.id.imgTesis)
        val idThesis = thesisList[position].thesisId.toString()
        val gambar = "https://digilib.ubaya.ac.id/img/nobook.png"
        imgThesis.loadImage(gambar, progressBar)
        txtJudulThesis.text = thesisList[position].thesisTitle
        txtPenulisTesis.text = thesisList[position].penulis
        txtTahunThesis.text = thesisList[position].tahunTerbit
        btnDetailTesis.setOnClickListener {
            val action = ThesisFragmentDirections.actionThesisDetail(idThesis)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateThesisList(newThesisList: ArrayList<Thesis>){
        thesisList.clear()
        thesisList.addAll(newThesisList)
        notifyDataSetChanged()
    }
}