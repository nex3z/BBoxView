package com.nex3z.bboxview

import android.graphics.PointF
import android.graphics.RectF
import kotlin.math.max
import kotlin.math.min

data class BBox(
    val from: PointF,
    var to: PointF = from,
    var label: String? = null
) {

    val rect: RectF
        get() = RectF(min(from.x, to.x), min(from.y, to.y), max(from.x, to.x), max(from.y, to.y))

    val center: PointF
        get() = PointF((from.x + to.x) / 2, (from.y + to.y) / 2)

}
