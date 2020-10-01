package com.nex3z.bboxview

import android.app.AlertDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_bbox.view.*

class BBoxView(
    context: Context,
    attrs: AttributeSet? = null
): FrameLayout(context, attrs) {

    var labelMode: Boolean = false
    var labels: Array<String> = emptyArray()
    var box: BBox? = null
        set(value) {
            if (value != null) {
                tv_vb_label.text = value.label
                layoutParams = LayoutParams(value.width.toInt(), value.height.toInt()).apply {
                    leftMargin = value.left.toInt()
                    topMargin = value.top.toInt()
                }
            } else {
                tv_vb_label.text = null
                layoutParams = null
            }
            field = value
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_bbox, this, true)

        tv_vb_label.setOnClickListener {
            if (labelMode) {
                AlertDialog.Builder(context).apply {
                    setItems(labels) { _, which ->
                        tv_vb_label.text = labels[which]
                    }
                }.create().show()
            }
        }
    }
}