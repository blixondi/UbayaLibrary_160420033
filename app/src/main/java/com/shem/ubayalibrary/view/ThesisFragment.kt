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
import com.shem.ubayalibrary.viewmodel.ThesisListViewModel

class ThesisFragment : Fragment() {
    private lateinit var viewModel: ThesisListViewModel
    private val thesisListAdapter = ThesisListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thesis, container, false)
    }

    fun observeViewModel(){
        viewModel.thesisLD.observe(viewLifecycleOwner, Observer {
            thesisListAdapter.updateThesisList(it)
        })

        viewModel.thesisLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtErrorThesis)
            if(it == true){
                txtError?.visibility = View.VISIBLE
            } else{
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewThesis = view?.findViewById<RecyclerView>(R.id.recViewThesis)
            val progressLoadThesis = view?.findViewById<ProgressBar>(R.id.progressBarThesis)
            if(it == true) {
                recViewThesis?.visibility = View.GONE
                progressLoadThesis?.visibility = View.VISIBLE
            } else {
                recViewThesis?.visibility = View.VISIBLE
                progressLoadThesis?.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ThesisListViewModel::class.java)
        viewModel.refresh()
        val recView = view.findViewById<RecyclerView>(R.id.recViewThesis)
        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.thesisRefreshLayout)
        val txtError = view.findViewById<TextView>(R.id.txtErrorThesis)
        val progressLoad = view.findViewById<ProgressBar>(R.id.progressBarThesis)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = thesisListAdapter
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