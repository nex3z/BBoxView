package com.nex3z.bboxview.sample

import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val from = PointF(
                Random.nextInt(0, bbc_container.width / 2).toFloat(),
                Random.nextInt(0, bbc_container.height / 2).toFloat()
            )
            val to = PointF(
                from.x + Random.nextInt(bbc_container.width / 4, bbc_container.width / 2),
                from.y + Random.nextInt(bbc_container.height / 4, bbc_container.height / 2)
            )
            val box = BBox(
                from = from,
                to = to,
                label = "Label ${count++}"
            )
            bbc_container.addBox(box)
        }

        btn_clear.setOnClickListener {
            bbc_container.clear()
            count = 0
        }
    }
}