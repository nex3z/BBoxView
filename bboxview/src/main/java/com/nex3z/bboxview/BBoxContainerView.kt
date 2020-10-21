package com.nex3z.bboxview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.children

class BBoxContainerView(
    context: Context,
    attrs: AttributeSet? = null
): FrameLayout(context, attrs) {

    private lateinit var mask: Bitmap
    private lateinit var maskCanvas: Canvas

    init {
        setWillNotDraw(false)
    }

    private val maskPen: Paint = Paint().apply {
        color = Color.argb(55, 0, 0, 0)
        style = Paint.Style.FILL
    }

    private val cropPen: Paint = Paint().apply {
        color = Color.argb(0, 255, 255, 255)
        style = Paint.Style.FILL
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mask = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        maskCanvas = Canvas(mask)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mask.eraseColor(Color.TRANSPARENT)
        maskCanvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), maskPen)
        var empty = true
        children
            .filter {it is BBoxView}
            .forEach {
                maskCanvas.drawRect(it.x, it.y, it.x + it.width, it.y + it.height, cropPen)
                empty = false
            }
        if (!empty) {
            canvas?.drawBitmap(mask, 0f, 0f, null)
        }
    }

    fun addBox(vararg boxes: BBox) {
        var bboxView: BBoxView
        for (box in boxes) {
            bboxView = BBoxView(context)
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