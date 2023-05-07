package com.shem.ubayalibrary.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shem.ubayalibrary.R
import com.shem.ubayalibrary.model.History
import com.shem.ubayalibrary.util.loadImage

class HistoryAdapter(val historyList: ArrayList<History>): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(var view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.history_item, parent,false)

        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val txtJudulHistory = holder.view.findViewById<TextView>(R.id.txtJudulHistory)
        val txtStatusPinjam = holder.view.findViewById<TextView>(R.id.txtStatusPinjam)
        val txtWaktuPinjam = holder.view.findViewById<TextView>(R.id.txtWaktuPinjam)
        val imgHistory = holder.view.findViewById<ImageView>(R.id.imgHistory)
        val progressBar4 = holder.view.findViewById<ProgressBar>(R.id.progressBarImgHistory)
        imgHistory.loadImage(historyList[position].gambar, progressBar4)
        txtJudulHistory.text = historyList[position].judul
        txtStatusPinjam.text = historyList[position].status
        txtWaktuPinjam.text = historyList[position].tanggal
        if(txtStatusPinjam.text == "kembali"){
            txtStatusPinjam.setTextColor(Color.parseColor("#59FB03"))
        } else{
            txtStatusPinjam.setTextColor(Color.parseColor("#FB0303"))
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    fun updateHistoryList(newStudentList: ArrayList<History>) {
        historyList.clear()
        historyList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}