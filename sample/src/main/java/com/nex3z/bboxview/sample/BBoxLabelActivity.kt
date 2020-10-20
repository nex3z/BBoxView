package com.nex3z.bboxview.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bbox_label.*

class BBoxLabelActivity : AppCompatActivity() {
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bbox_label)
        initView()
    }

    private fun initView() {
        btn_abl_add.setOnClickListener {
            bbav_abl_label.addBox("Label ${count++}")
        }

        btn_abl_clear.setOnClickListener {
            bbav_abl_label.clear()
            count = 0
        }
    }
}