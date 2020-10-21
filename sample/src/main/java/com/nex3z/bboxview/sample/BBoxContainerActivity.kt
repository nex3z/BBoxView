package com.nex3z.bboxview.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nex3z.bboxview.BBox
import kotlinx.android.synthetic.main.activity_bbox_container.*
import kotlin.random.Random

class BBoxContainerActivity : AppCompatActivity() {
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bbox_container)
        initView()
    }

    private fun initView() {
        btn_add.setOnClickListener {
            val maxWidth = bbcv_container.width
            val maxHeight = bbcv_container.height
            val xmin = Random.nextInt(0, maxWidth / 2).toFloat() / maxWidth
            val ymin = Random.nextInt(0, maxHeight / 2).toFloat() / maxHeight
            val xmax = xmin + Random.nextInt(maxWidth / 4, maxWidth / 2).toFloat()  / maxWidth
            val ymax = ymin + Random.nextInt(maxHeight / 4, maxHeight / 2).toFloat() / maxHeight
            val box = BBox(
                label = "Label ${count++}",
                xmin = xmin,
                ymin = ymin,
                xmax = xmax,
                ymax = ymax
            )
            bbcv_container.addBox(box)
        }

        btn_clear.setOnClickListener {
            bbcv_container.clear()
            count = 0
        }
    }
}
