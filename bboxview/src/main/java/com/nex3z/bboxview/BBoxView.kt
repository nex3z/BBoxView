package com.nex3z.bboxview

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
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

    fun getSelectedAnchor(x: Int, y: Int): Anchor? {
        return when {
            iv_vb_anchor_top_left.contains(x, y) -> {
                Log.v(TAG, "getSelectedAnchor(): selected iv_vb_anchor_top_left")
                Anchor.TOP_LEFT
            }
            iv_vb_anchor_top_right.contains(x, y) -> {
                Log.v(TAG, "getSelectedAnchor(): selected iv_vb_anchor_top_right")
                Anchor.TOP_RIGHT
            }
            iv_vb_anchor_bottom_left.contains(x, y) -> {
                Log.v(TAG, "getSelectedAnchor(): selected iv_vb_anchor_bottom_left")
                Anchor.BOTTOM_LEFT
            }
            iv_vb_anchor_bottom_right.contains(x, y) -> {
                Log.v(TAG, "getSelectedAnchor(): selected iv_vb_anchor_bottom_right")
                Anchor.BOTTOM_RIGHT
            }
            iv_vb_anchor_center.contains(x, y) -> {
                Log.v(TAG, "getSelectedAnchor(): selected iv_vb_anchor_bottom_right")
                Anchor.CENTER
            }
            else -> {
                Log.v(TAG, "getSelectedAnchor(): selected null")
                return null
            }
        }
    }
    
    enum class Anchor {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, CENTER
    }

    private fun View.contains(x: Int, y: Int): Boolean {
        val rect = Rect()
        getHitRect(rect)
        return rect.contains(x, y)
    }

    companion object {
        private val TAG: String = BBoxView::class.java.simpleName
    }
}