package com.ven.murainoyakububiola.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ven.murainoyakububiola.MainApplication
import com.ven.murainoyakububiola.R
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.view.adapter.FilterAdapter
import com.ven.murainoyakububiola.view.base.BaseActivity
import com.ven.murainoyakububiola.view.base.CustomItemClickListener
import com.ven.murainoyakububiola.viewmodel.FilteredListViewModel
import kotlinx.android.synthetic.main.activity_main.*


class FilteredListActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener,
    CustomItemClickListener<FilterEntity> {


    private var viewModel: FilteredListViewModel? = null
    private lateinit var adapter: FilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(FilteredListViewModel::class.java)

         views()


        setToolbar( "Filter Details")


    }


    private fun views(){

        adapter = FilterAdapter(this)
        val manager = LinearLayoutManager(this)
        _recyclerView__filter?.layoutManager = manager
        _recyclerView__filter?.adapter = adapter

        viewModel?.errorMessage?.observe(this, Observer {
            progressBar.visibility = View.GONE
            swipeRefresh?.isRefreshing = false

        })

        viewModel?.successMessage?.observe(this, Observer {
            progressBar.visibility = View.GONE
            swipeRefresh?.isRefreshing = false

        })

        viewModel?.getFilteredList?.observe(this, Observer {
            loadListFromObserver(it)
        })


        swipeRefresh?.setOnRefreshListener(this)

        checkFileWritePermission()

    }

    private fun loadListFromObserver(it : List<FilterEntity>){
        if(it.isEmpty()){
            viewModel?.loadFromServer()
            progressBar.visibility = View.VISIBLE

        }else{
            progressBar.visibility = View.GONE
            adapter.addItems(it)


        }
    }

    override fun onRefresh() {
        viewModel?.loadFromServer()
    }



    override fun onItemClick(vararg args: FilterEntity) {

        if(args[0] != null) {
            startActivity( Intent(MainApplication.instance?.applicationContext, CarFilteredListActivity::class.java))
            //send to viewmodel to handle filtering and it will be observe by CarFilteredListActivity
            viewModel?.handleCarFiltering(args[0])
        }
    }

    private fun checkFileWritePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                100
            )
        }
    }
}
