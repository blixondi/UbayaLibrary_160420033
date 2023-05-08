package com.shem.ubayalibrary.view

import android.graphics.Color
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
import com.shem.ubayalibrary.viewmodel.BookDetailViewModel


class BookDetailFragment : Fragment() {
    private lateinit var viewModel: BookDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var book_id = ""

        arguments?.let{
            book_id = BookDetailFragmentArgs.fromBundle(requireArguments()).bookId
        }
        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        viewModel.fetch(book_id)

        val txtJudulDetail = view.findViewById<TextView>(R.id.txtJudulDetail)
        val txtDetailPengarang = view.findViewById<TextView>(R.id.txtDetailPengarang)
        val txtPenerbit = view.findViewById<TextView>(R.id.txtPenerbit)
        val txtDimensi = view.findViewById<TextView>(R.id.txtDimensi)
        val txtIsbn = view.findViewById<TextView>(R.id.txtIsbn)
        val txtKetersediaan = view.findViewById<TextView>(R.id.txtKetersediaan)
        val txtSinopsis = view.findViewById<TextView>(R.id.txtSinopsis)
        val imgDetailBuku = view.findViewById<ImageView>(R.id.imgDetailBuku)
        val progressBar3 = view.findViewById<ProgressBar>(R.id.progressBar3)

        viewModel.bookLD.observe(viewLifecycleOwner) {book->
            txtJudulDetail.text = book.bookTitle
            txtDetailPengarang.text = "Pengarang ${book.penulis}"
            txtPenerbit.text = "Penerbit: ${book.penerbit}, ${book.tahunTerbit}"
            txtDimensi.text = "Dimensi: ${book.dimensi}"
            txtIsbn.text = "ISBN: ${book.isbn}"
            txtKetersediaan.text = "Ketersediaan: ${book.ketersediaan}"
            if(book.ketersediaan == "Tersedia"){
                txtKetersediaan.setTextColor(Color.parseColor("#59FB03"))
            } else{
                txtKetersediaan.setTextColor(Color.parseColor("#FB0303"))
            }
            txtSinopsis.text = book.sinopsis
            imgDetailBuku.loadImage(book.gambar, progressBar3)
        }
    }

}