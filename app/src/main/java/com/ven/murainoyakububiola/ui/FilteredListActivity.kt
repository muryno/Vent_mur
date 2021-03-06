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
import kotlinx.android.synthetic.main.activity_main.toolbar


class FilteredListActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener,
    CustomItemClickListener<FilterEntity> {


    private var viewModel: FilteredListViewModel? = null
    private lateinit var adapter: FilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar.title = "Filter List"
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(FilteredListViewModel::class.java)

         views()





    }


    private fun views(){

        checkFileWritePermission()



        adapter = FilterAdapter(this)
        val manager = LinearLayoutManager(this)
        _recyclerView__filter?.layoutManager = manager
        _recyclerView__filter?.adapter = adapter

        //
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
        val i = Intent(this,CarFilteredListActivity::class.java)
        i.putExtra("data",args[0])

        startActivity(i)
    }



    private fun checkFileWritePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                100
            )
        }
    }


}
