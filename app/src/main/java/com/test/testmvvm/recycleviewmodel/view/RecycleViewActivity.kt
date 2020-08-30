package com.test.testmvvm.recycleviewmodel.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.testmvvm.R
import com.test.testmvvm.databinding.ActivityRecycleviewBinding
import com.test.testmvvm.recycleviewmodel.recyclerviewmodel.RecyclerViewModel

class RecycleViewActivity : AppCompatActivity(){
    private lateinit var activityRecyclerviewBinding: ActivityRecycleviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRecyclerviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycleview)
        var  viewModel = RecyclerViewModel.newInstance(activityRecyclerviewBinding,this)
        viewModel.init()
    }
}

