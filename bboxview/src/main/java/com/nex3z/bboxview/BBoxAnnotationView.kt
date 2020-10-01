package com.nex3z.bboxview

import android.content.Context
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

class BBoxAnnotationView(
    context: Context,
    attrs: AttributeSet? = null
): FrameLayout(context, attrs) {

    var labels: Array<String> = emptyArray()
    private var current: BBoxView? = null

    init {
        setWillNotDraw(false)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.v("BBoxAnnotationView", "onTouchEvent(): event = $event")
        if (event == null) { return false }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                current = BBoxView(context).apply {
                    box = BBox(location = RectF(event.x, event.y, event.x, event.y))
                    labelMode = true
                    labels = this@BBoxAnnotationView.labels
                }
                addView(current)
            }
            MotionEvent.ACTION_MOVE -> {
                current?.apply {
                    box?.let {
                        box = BBox(location = RectF(it.left, it.top, event.x, event.y))
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                current = null
            }
            MotionEvent.ACTION_CANCEL -> {
                current = null
            }
        }
        return true
    }
}