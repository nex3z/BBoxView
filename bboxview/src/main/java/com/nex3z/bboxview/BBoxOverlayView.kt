package com.nex3z.bboxview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BBoxOverlayView(
    context: Context,
    attrs: AttributeSet? = null
): View(context, attrs) {

    private val boxes: MutableList<BBox> = mutableListOf()

    private val boxFillPaint: Paint = Paint().apply {
        style = Paint.Style.FILL
        color = 0x2200ff00
    }
    private val boxBorderPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = context.dp2px(2f)
        color = Color.GREEN
    }
    private val textPaint: Paint = Paint().apply {
        textSize = context.dp2px(12f)
        color = Color.BLACK
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

    fun addBox(box: BBox) {
        this.boxes.add(box)
        invalidate()
    }

    fun clear() {
        boxes.clear()
        invalidate()
    }
}