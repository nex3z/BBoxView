package com.nex3z.bboxview

import android.app.AlertDialog
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_bbox.view.*

class BBoxView(
    context: Context,
    attrs: AttributeSet? = null
): FrameLayout(context, attrs) {

    var labelMode: Boolean = false
    var labels: Array<String> = emptyArray()
    private val hitRect: Rect by lazy { Rect() }
    var resizeMode: Boolean = false
    private var selectedAnchor: Anchor? = null

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

        iv_vb_switch_reize.setOnClickListener {
            resizeMode = !resizeMode
            if (resizeMode) {
                tv_vb_label.visibility = View.GONE
                iv_vb_anchor_top_left.visibility = View.VISIBLE
                iv_vb_anchor_top_right.visibility = View.VISIBLE
                iv_vb_anchor_bottom_left.visibility = View.VISIBLE
                iv_vb_anchor_bottom_right.visibility = View.VISIBLE
            } else {
                tv_vb_label.visibility = View.VISIBLE
                iv_vb_anchor_top_left.visibility = View.GONE
                iv_vb_anchor_top_right.visibility = View.GONE
                iv_vb_anchor_bottom_left.visibility = View.GONE
                iv_vb_anchor_bottom_right.visibility = View.GONE
            }
        }
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

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null || !resizeMode) return false
        Log.v(TAG, "x = $x, y = $y, width = $width, height = $height")
        Log.v(TAG, "event = $event")

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                selectedAnchor = getSelectedAnchor(event)
            }
            MotionEvent.ACTION_MOVE -> {
                when (selectedAnchor) {
                    Anchor.TOP_LEFT -> {
                        x = x + event.x
                        y = y + event.y
                    }
                    Anchor.BOTTOM_RIGHT -> {
                        layoutParams.width = (event.x).toInt()
                        layoutParams.height = (event.y).toInt()
                        requestLayout()
                    }
                }
            }
        }
        return true
    }

    private fun getSelectedAnchor(event: MotionEvent): Anchor? {
        if (iv_vb_anchor_top_left.contains(event)) {
            Log.v(TAG, "getSelectedAnchor(): selected iv_vb_anchor_top_left")
            return Anchor.TOP_LEFT
        } else if (iv_vb_anchor_top_right.contains(event)) {
            Log.v(TAG, "getSelectedAnchor(): selected iv_vb_anchor_top_right")
            return Anchor.TOP_RIGHT
        } else if (iv_vb_anchor_bottom_left.contains(event)) {
            Log.v(TAG, "getSelectedAnchor(): selected iv_vb_anchor_bottom_left")
            return Anchor.BOTTOM_LEFT
        } else if (iv_vb_anchor_bottom_right.contains(event)) {
            Log.v(TAG, "getSelectedAnchor(): selected iv_vb_anchor_bottom_right")
            return Anchor.BOTTOM_RIGHT
        } else {
            Log.v(TAG, "getSelectedAnchor(): selected null")
            return null
        }
    }
    
    private enum class Anchor {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    private fun View.contains(event: MotionEvent): Boolean {
        getHitRect(hitRect)
        return hitRect.contains(event.x.toInt(), event.y.toInt())
    }

    companion object {
        private val TAG: String = BBoxView::class.java.simpleName
    }
}