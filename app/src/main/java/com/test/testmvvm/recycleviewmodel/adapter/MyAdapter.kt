package com.test.testmvvm.recycleviewmodel.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.testmvvm.R
import com.test.testmvvm.recycleviewmodel.bean.TestBean

class MyAdapter(private var list: ArrayList<TestBean>, private var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item,null)
        return MyViewHolder(itemView)
    }

    fun setListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyViewHolder){
            var testBean = list[position]
            holder.itemView.setOnClickListener {
                onItemClickListener.onItemClick(position)
            }
            holder.name.text = testBean.name
            holder.id.text = testBean.id.toString()
            holder.high.text = testBean.high.toString()
            holder.age.text = testBean.age.toString()
        }
    }

    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
          var name:TextView = itemView.findViewById(R.id.name)
          var id:TextView = itemView.findViewById(R.id.id)
          var high:TextView = itemView.findViewById(R.id.high)
          var age:TextView = itemView.findViewById(R.id.age)
    }
    
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}