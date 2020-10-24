package com.nex3z.bboxview.label

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.nex3z.bboxview.R
import kotlinx.android.synthetic.main.view_label_box.view.*

class LabelBoxView(
    context: Context,
    attrs: AttributeSet? = null
): FrameLayout(context, attrs) {

    var label: String?
        set(value) {
            tv_vlb_label.text = value
        }
        get() = tv_vlb_label.text.toString()

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_label_box, this, true)
    }

    fun getSelectedAnchor(x: Int, y: Int): Anchor? {
        return when {
            contains(iv_vlb_anchor_top_left, x, y) -> {
                Log.v(TAG, "getSelectedAnchor(): selected iv_vlb_anchor_top_left")
                Anchor.TOP_LEFT
            }
            contains(iv_vlb_anchor_top_right, x, y) -> {
                Log.v(TAG, "getSelectedAnchor(): selected iv_vlb_anchor_top_right")
                Anchor.TOP_RIGHT
            }
            contains(iv_vlb_anchor_bottom_left, x, y) -> {
                Log.v(TAG, "getSelectedAnchor(): selected iv_vlb_anchor_bottom_left")
                Anchor.BOTTOM_LEFT
            }
            contains(iv_vlb_anchor_bottom_right, x, y) -> {
                Log.v(TAG, "getSelectedAnchor(): selected iv_vlb_anchor_bottom_right")
                Anchor.BOTTOM_RIGHT
            }
            contains(iv_vlb_anchor_center, x, y) -> {
                Log.v(TAG, "getSelectedAnchor(): selected iv_vlb_anchor_bottom_right")
                Anchor.CENTER
            }
            else -> {
                Log.v(TAG, "getSelectedAnchor(): selected null")
                return null
            }
        }
    }

    fun contains(x: Int, y: Int): Boolean {
        return contains(this, x, y)
    }
    
    enum class Anchor {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, CENTER
    }

    private fun contains(view: View, x: Int, y: Int): Boolean {
        val rect = Rect()
        view.getHitRect(rect)
        return rect.contains(x, y)
    }

    companion object {
        private val TAG: String = LabelBoxView::class.java.simpleName
    }
}