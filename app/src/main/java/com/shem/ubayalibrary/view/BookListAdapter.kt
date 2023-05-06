package com.shem.ubayalibrary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shem.ubayalibrary.R
import com.shem.ubayalibrary.model.Book
import com.shem.ubayalibrary.util.loadImage

class BookListAdapter(val bookList: ArrayList<Book>):RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {
    class BookViewHolder(var view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_item, parent,false)

        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val txtJudulBuku =holder.view.findViewById<TextView>(R.id.txtJudulBuku)
        val txtPenulisBuku = holder.view.findViewById<TextView>(R.id.txtPenulisBuku)
        val txtTahunBuku = holder.view.findViewById<TextView>(R.id.txtTahunBuku)
        val btnDetailBuku = holder.view.findViewById<Button>(R.id.btnDetailBuku)
        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        val imgBuku = holder.view.findViewById<ImageView>(R.id.imgBuku)
        val idBuku = bookList[position].bookId
        imgBuku.loadImage(bookList[position].gambar, progressBar)
        txtJudulBuku.text = bookList[position].bookTitle
        txtPenulisBuku.text = bookList[position].penulis
        txtTahunBuku.text = bookList[position].tahunTerbit
        btnDetailBuku.setOnClickListener {

        }
    }

    fun updateBookList(newStudentList: ArrayList<Book>) {
        bookList.clear()
        bookList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}