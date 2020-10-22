package com.nex3z.bboxview.box

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.nex3z.bboxview.R
import kotlinx.android.synthetic.main.view_bbox.view.*

class BBoxView(
    context: Context,
    attrs: AttributeSet? = null
): FrameLayout(context, attrs) {

    var label: String?
        set(value) {
            tv_vb_label.text = value
        }
        get() = tv_vb_label.text.toString()

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_bbox, this, true)
    }
}