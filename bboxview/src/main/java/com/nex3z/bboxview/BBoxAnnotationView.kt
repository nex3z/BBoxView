package com.nex3z.bboxview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View

class BBoxAnnotationView(
    context: Context,
    attrs: AttributeSet? = null
): View(context, attrs) {

    private val boxes: MutableList<BBox> = mutableListOf()
    private var currentBox: BBox? = null

    private val boxFillPaint: Paint = Paint().apply {
        style = Paint.Style.FILL
        color = 0x2200ff00
    }
    private val boxBorderPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = dp2px(2f)
        color = Color.GREEN
    }
    private val textPaint: Paint = Paint().apply {
        textSize = dp2px(12f)
        color = Color.BLACK
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) { return false }

        val point = PointF(event.x, event.y)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                BBox(point).apply {
                    currentBox = this
                    boxes.add(this)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                currentBox?.let {
                    it.to = point
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                currentBox = null
            }
            MotionEvent.ACTION_CANCEL -> {
                currentBox = null
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) { return }

        for (box in boxes) {
            canvas.drawRect(box.rect, boxFillPaint)
            canvas.drawRect(box.rect, boxBorderPaint)
            box.label?.let {
                val center = box.center
                canvas.drawText(it, center.x, center.y, textPaint)
            }
        }
    }

    fun clear() {
        boxes.clear()
        invalidate()
    }

    private fun dp2px(dp: Float) =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

    companion object {
        private val LOG_TAG: String = BBoxAnnotationView::class.java.simpleName
    }
}
