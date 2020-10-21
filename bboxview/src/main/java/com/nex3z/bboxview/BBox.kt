package com.nex3z.bboxview

data class BBox(
    val label: String? = null,
    val xmin: Float,
    val ymin: Float,
    val xmax: Float,
    val ymax: Float
) {

    val width: Float = xmax - xmin
    val height: Float = ymax - ymin

//    val left: Int = location.left
//    val top: Int = location.top
//    val right: Int = location.right
//    val bottom: Int = location.bottom
//
//    val center: Point
//        get() = Point((location.left + location.right) / 2, (location.top + location.bottom) / 2)

}
