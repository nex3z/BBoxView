package com.nex3z.bboxview.util

import android.content.Context
import android.util.TypedValue

fun Context.dp2px(dp: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

fun Context.dp2pxInt(dp: Int): Int = dp2px(dp.toFloat()).toInt()

fun Context.dp2pxInt(dp: Float): Int = dp2px(dp).toInt()
