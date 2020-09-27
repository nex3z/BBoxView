package com.nex3z.bboxview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout


class BBoxContainer(
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
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child is BBoxView) {
                maskCanvas.drawRect(child.x, child.y, child.x + child.width, child.y + child.height, cropPen)
                empty = false
            }
        }
        if (!empty) {
            canvas?.drawBitmap(mask, 0f, 0f, null)
        }
    }

    fun addBox(box: BBox) {
        Log.v(LOG_TAG, "addBox(): box = $box")
        val bboxView = BBoxView(context).apply {
            this.box = box
        }
        val params = LayoutParams(box.rect.width().toInt(), box.rect.height().toInt()).apply {
            leftMargin = box.rect.left.toInt()
            topMargin = box.rect.top.toInt()
        }
        addView(bboxView, params)
    }

    fun clear() {
        removeAllViews()
    }

    companion object {
        private val LOG_TAG = BBoxContainer::class.java.simpleName
    }
}