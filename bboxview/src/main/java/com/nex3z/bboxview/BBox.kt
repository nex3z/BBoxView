package com.nex3z.bboxview

import android.graphics.PointF
import android.graphics.RectF

data class BBox(
    val label: String? = null,
    private val location: RectF
) {

    val width: Float = location.width()
    val height: Float = location.height()
    val left: Float = location.left
    val top: Float = location.top
    val right: Float = location.right
    val bottom: Float = location.bottom

    val center: PointF
        get() = PointF((location.left + location.right) / 2, (location.top + location.bottom) / 2)

}
