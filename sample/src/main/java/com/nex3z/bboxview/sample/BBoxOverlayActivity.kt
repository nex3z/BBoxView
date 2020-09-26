package com.nex3z.bboxview.sample

import android.graphics.PointF
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.nex3z.bboxview.BBox
import kotlinx.android.synthetic.main.activity_bbox_overlay.*

class BBoxOverlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bbox_overlay)
        initView()
    }
    
    private fun initView() {
        btn_add.setOnClickListener {
            val box = BBox(
                from = PointF(et_left.getFloat(), et_top.getFloat()),
                to = PointF(et_right.getFloat(), et_bottom.getFloat())
            )
            bbox_overlay.addBox(box)
        }
        btn_clear.setOnClickListener {
            bbox_overlay.clear()
        }
    }

    private fun EditText.getFloat(): Float = text.toString().toFloat()
}