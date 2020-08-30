package com.test.testmvvm.recycleviewmodel.recyclerviewmodel

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.test.testmvvm.databinding.ActivityRecycleviewBinding
import com.test.testmvvm.recycleviewmodel.adapter.MyAdapter
import com.test.testmvvm.recycleviewmodel.bean.TestBean

class RecyclerViewModel(private var activityRecyclerviewBinding: ActivityRecycleviewBinding, var context: Context) :MyAdapter.OnItemClickListener{
    private lateinit var list: ArrayList<TestBean>

    companion object {
        fun newInstance(param1: ActivityRecycleviewBinding, param2: Context): RecyclerViewModel {
            return RecyclerViewModel(param1, param2)
        }
    }

    fun init() {
        list = ArrayList<TestBean>()
        for (i in 1..50) {
            val testBean = TestBean("张三$i", i, i * 100f, 1)
            list.add(testBean)
        }
        val myAdapter = MyAdapter(list, context)
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        activityRecyclerviewBinding.recyclerview.layoutManager = manager
        activityRecyclerviewBinding.recyclerview.adapter = myAdapter
        myAdapter.setListener(this)

    }

    fun test(){

    }

    override fun onItemClick(position:Int) {
        Toast.makeText(context,list[position].toString(),Toast.LENGTH_LONG).show()
    }

}