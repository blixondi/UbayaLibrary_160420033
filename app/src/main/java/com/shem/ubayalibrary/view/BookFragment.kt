package com.shem.ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shem.ubayalibrary.R
import com.shem.ubayalibrary.viewmodel.BookListViewModel


class BookFragment : Fragment() {
    private lateinit var viewModel: BookListViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    fun observeViewModel(){
        viewModel.booksLD.observe(viewLifecycleOwner, Observer {
            bookListAdapter.updateBookList(it)
        })

        viewModel.bookLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtError = view?.findViewById<TextView>(R.id.txtBookError)
            if(it == true){
                txtError?.visibility = View.VISIBLE
            } else{
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewBook = view?.findViewById<RecyclerView>(R.id.recViewBook)
            val progressLoadBook = view?.findViewById<ProgressBar>(R.id.progressLoadBook)
            if(it == true) {
                recViewBook?.visibility = View.GONE
                progressLoadBook?.visibility = View.VISIBLE
            } else {
                recViewBook?.visibility = View.VISIBLE
                progressLoadBook?.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookListViewModel::class.java)
        viewModel.refresh()
        val recView = view.findViewById<RecyclerView>(R.id.recViewBook)
        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.bookRefreshLayout)
        val txtError = view.findViewById<TextView>(R.id.txtBookError)
        val progressLoad = view.findViewById<ProgressBar>(R.id.progressLoadBook)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = bookListAdapter
        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }
}