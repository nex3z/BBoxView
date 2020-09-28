package com.nex3z.bboxview.sample

import android.graphics.RectF
import android.os.Bundle
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
            val box = BBox(
                label = "Label ${count++}",
                location = generateRandomLocation()
            )
            bbc_container.addBox(box)
        }

        btn_clear.setOnClickListener {
            bbc_container.clear()
            count = 0
        }
    }

    private fun generateRandomLocation(): RectF {
        val maxWidth = bbc_container.width
        val maxHeight = bbc_container.height
        val left =  Random.nextInt(0, maxWidth / 2).toFloat()
        val top =  Random.nextInt(0, maxHeight / 2).toFloat()
        val right = left + Random.nextInt(maxWidth / 4, maxWidth / 2)
        val bottom = top + Random.nextInt(maxHeight / 4, maxHeight / 2)
        return RectF(left, top, right, bottom)
    }
}