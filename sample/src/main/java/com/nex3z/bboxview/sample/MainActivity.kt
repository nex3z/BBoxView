package com.nex3z.bboxview.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btn_bbox_container.setOnClickListener {
            Intent(this, BBoxContainerActivity::class.java).apply(this::startActivity)
        }
        btn_bbox_label.setOnClickListener {
            Intent(this, BBoxLabelActivity::class.java).apply(this::startActivity)
        }
    }
}