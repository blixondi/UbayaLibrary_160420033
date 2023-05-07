package com.shem.ubayalibrary.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shem.ubayalibrary.R
import com.shem.ubayalibrary.viewmodel.HistoryViewModel
import org.w3c.dom.Text


class HistoryFragment : Fragment() {
    private lateinit var viewModel: HistoryViewModel
    private val historyListAdapter = HistoryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    fun observeViewModel(){
        viewModel.historysLD.observe(viewLifecycleOwner, Observer{
            historyListAdapter.updateHistoryList(it)
        })

        viewModel.historyLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtErrorHistory)
            if(it == true){
                txtError?.visibility = View.VISIBLE
            } else{
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            val recViewHistory = view?.findViewById<RecyclerView>(R.id.historyRec)
            val progressLoadHistory = view?.findViewById<ProgressBar>(R.id.progressBarHistory)
            if(it==true){
                recViewHistory?.visibility = View.GONE
                progressLoadHistory?.visibility = View.VISIBLE
            } else{
                recViewHistory?.visibility = View.VISIBLE
                progressLoadHistory?.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shared: SharedPreferences? = activity?.getSharedPreferences("UbayaLibrary",
            AppCompatActivity.MODE_PRIVATE)
        val checkid = shared?.getString(LoginFragment.currentid, (-1).toString())
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        if (checkid != null) {
            viewModel.refresh(checkid)
        }
        val recView = view.findViewById<RecyclerView>(R.id.historyRec)
        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.historyRefreshLayout)
        val txtError = view.findViewById<TextView>(R.id.txtErrorHistory)
        val progressLoad = view.findViewById<ProgressBar>(R.id.progressBarHistory)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = historyListAdapter
        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            if (checkid != null) {
                viewModel.refresh(checkid)
            }
            refreshLayout.isRefreshing = false
        }
    }

}