package com.test.testmvvm.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.study.xuan.gifshow.widget.stlview.callback.OnReadCallBack
import com.study.xuan.gifshow.widget.stlview.widget.STLView
import com.study.xuan.gifshow.widget.stlview.widget.STLViewBuilder
import com.test.testmvvm.R

class KtActivity : AppCompatActivity(),View.OnClickListener{
    private lateinit var button3 :Button
    private lateinit var button4 :Button
    private lateinit var button5 :Button
    private lateinit var mStl :STLView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt)
        setView()
    }

    private fun setView(){
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        mStl = findViewById(R.id.stl_view)

        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener {
            finish()
        }
        STLViewBuilder.init(mStl).Assets(this, "actionpro.STL").build()
        mStl.setTouch(true)
        mStl.setScale(true)
        mStl.setRotate(true)
        mStl.setSensor(true)
        mStl.setOnReadCallBack(object : OnReadCallBack() {
        })
        Log.i((0 and 255).toString(),"setView")
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button3 ->Toast.makeText(this,"button3 click",Toast.LENGTH_SHORT).show()
            R.id.button4 ->Toast.makeText(this,"button4 click",Toast.LENGTH_SHORT).show()
        }
    }
}