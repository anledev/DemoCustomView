package com.anledev.democustomview.extensions

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Px

fun ViewGroup.setContentView(@LayoutRes id: Int) {
    LayoutInflater.from(context).inflate(id, this, true)
}

infix fun Boolean.show(view: View) {
    view.visibility = if (this) View.VISIBLE else View.GONE
}

fun Context.loadAttrs(attrs: AttributeSet?, attrType: IntArray, function: (TypedArray) -> Unit) {
    if (attrs == null) return
    val a = obtainStyledAttributes(attrs, attrType)
    function(a)
    a.recycle()
}

fun ViewGroup.setPadding(@Px size: Int){
    setPadding(size, size, size, size)
}
