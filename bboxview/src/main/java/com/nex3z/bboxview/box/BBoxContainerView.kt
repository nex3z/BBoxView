package com.nex3z.bboxview.box

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.nex3z.bboxview.BBox

open class BBoxContainerView(
    context: Context,
    attrs: AttributeSet? = null
): FrameLayout(context, attrs) {

    fun addBox(vararg boxes: BBox) {
        var bboxView: BBoxView
        for (box in boxes) {
            bboxView = BBoxView(context).apply { label = box.label }
            val params = LayoutParams((box.width * width).toInt(), (box.height * height).toInt())
            params.leftMargin =  (box.xmin * width).toInt()
            params.topMargin = (box.ymin * height).toInt()
            addView(bboxView, params)
        }
    }

    fun clear() {
        removeAllViews()
    }
}