package com.nex3z.bboxview.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bbox_annotation.*

class BBoxAnnotationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bbox_annotation)
        initView()
    }

    private fun initView() {
        bbav_annotation.labels = resources.getStringArray(R.array.labels)
    }
}