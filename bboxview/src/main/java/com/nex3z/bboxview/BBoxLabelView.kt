package com.nex3z.bboxview

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import kotlin.math.max
import kotlin.math.min

class BBoxLabelView(
    context: Context,
    attrs: AttributeSet? = null
): FrameLayout(context, attrs) {

    private var lastX: Int = 0
    private var lastY: Int = 0
    private var resizeView: BBoxView? = null
    private var resizeAnchor: BBoxView.Anchor? = null
    private val boxMinSize: Int = context.dp2pxInt(128)
    private val defaultPosition: Rect = Rect(0, 0, context.dp2pxInt(256), context.dp2pxInt(256))

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) { return false }

        var processed = false
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                resizeView = null
                resizeAnchor = null
                for (view in getChildren()) {
                    val params = view.layoutParams as LayoutParams
                    val anchor = view.getSelectedAnchor(
                        event.x.toInt() - params.leftMargin,
                        event.y.toInt() - params.topMargin
                    )
                    if (anchor != null) {
                        resizeView = view
                        resizeAnchor = anchor
                        processed = true
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                var dx = event.x.toInt() - lastX
                var dy = event.y.toInt() - lastY
                resizeView?.let {
                    val params = it.layoutParams as LayoutParams
                    val layoutParams = when (resizeAnchor) {
                        BBoxView.Anchor.TOP_LEFT -> {
                            dx = max(min(dx, params.width - boxMinSize), -params.leftMargin)
                            dy = max(min(dy, params.height - boxMinSize), -params.topMargin)
                            LayoutParams(params).apply {
                                this.leftMargin += dx
                                this.topMargin += dy
                                this.width -= dx
                                this.height -= dy
                            }
                        }
                        BBoxView.Anchor.TOP_RIGHT -> {
                            dx = min(max(dx, boxMinSize - params.width), width - params.leftMargin - params.width)
                            dy = max(min(dy, params.height - boxMinSize), -params.topMargin)
                            LayoutParams(params).apply {
                                this.topMargin += dy
                                this.width += dx
                                this.height -= dy
                            }
                        }
                        BBoxView.Anchor.BOTTOM_LEFT -> {
                            dx = max(min(dx, params.width - boxMinSize), -params.leftMargin)
                            dy = min(max(dy, boxMinSize - params.height), height - params.topMargin - params.height)
                            LayoutParams(params).apply {
                                this.leftMargin += dx
                                this.width -= dx
                                this.height += dy
                            }
                        }
                        BBoxView.Anchor.BOTTOM_RIGHT -> {
                            dx = min(max(dx, boxMinSize - params.width), width - params.leftMargin - params.width)
                            dy = min(max(dy, boxMinSize - params.height), height - params.topMargin - params.height)
                            LayoutParams(params).apply {
                                this.width += dx
                                this.height += dy
                            }
                        }
                        BBoxView.Anchor.CENTER -> {
                            dx = min(max(dx, -params.leftMargin), width - params.leftMargin - params.width)
                            dy = min(max(dy, -params.topMargin), height - params.topMargin - params.height)
                            LayoutParams(params).apply {
                                this.leftMargin += dx
                                this.topMargin += dy
                            }
                        }
                        null -> { null }
                    }
                    if (layoutParams != null) {
                        it.layoutParams = layoutParams
                        it.requestLayout()
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                resizeView = null
                resizeAnchor = null
            }
            MotionEvent.ACTION_CANCEL -> {
                resizeView = null
                resizeAnchor = null
            }
        }

        lastX = event.x.toInt()
        lastY = event.y.toInt()
        return processed
    }

    fun addBox(label: String, position: Rect = defaultPosition) {
        val box = BBoxView(context).apply {
            this.layoutParams = LayoutParams(position.width(), position.height()).apply {
                leftMargin = position.left
                rightMargin = position.right
            }
            this.label = label
        }
        addView(box)
    }

    fun getBoxes(): List<BBox> {
        return getChildren()
            .map {
                val params = it.layoutParams as LayoutParams
                BBox(
                    label = it.label,
                    location = Rect(
                        params.leftMargin,
                        params.topMargin,
                        params.leftMargin + params.width,
                        params.topMargin + params.height
                    )
                )
            }
            .toList()
    }

    private fun getChildren(): List<BBoxView> = (0.until(childCount))
        .map { getChildAt(it) }
        .filterIsInstance<BBoxView>()
        .toList()

    fun clear() {
        removeAllViews()
    }
}