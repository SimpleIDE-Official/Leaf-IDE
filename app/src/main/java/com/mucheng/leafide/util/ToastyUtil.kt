package com.mucheng.leafide.util

import android.content.Context
import android.view.Gravity
import es.dmoral.toasty.Toasty

fun toast(context: Context, message: Int, duration: Int = Toasty.LENGTH_SHORT) {
    val toast = Toasty.error(context, message)
    toast.setGravity(Gravity.TOP, 0, 50)
    toast.show()
}