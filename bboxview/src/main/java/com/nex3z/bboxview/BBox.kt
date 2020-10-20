package com.nex3z.bboxview

import android.graphics.Point
import android.graphics.Rect

data class BBox(
    val label: String? = null,
    private val location: Rect
) {

    val width: Int = location.width()
    val height: Int = location.height()
    val left: Int = location.left
    val top: Int = location.top
    val right: Int = location.right
    val bottom: Int = location.bottom

    val center: Point
        get() = Point((location.left + location.right) / 2, (location.top + location.bottom) / 2)

}
