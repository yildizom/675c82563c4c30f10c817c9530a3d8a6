package com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.list.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt


class SatelliteDividerItemDecoration(context: Context, private val mOrientation: Int):
    DividerItemDecoration(context, mOrientation) {

    private var mDivider: Drawable? = null
    private val attrs = intArrayOf(android.R.attr.listDivider)
    private val mBounds = Rect()

    init {
        val a = context.obtainStyledAttributes(attrs)
        mDivider = a.getDrawable(0)
        a.recycle()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent)
        } else {
            super.onDraw(c, parent, state)
        }
        drawVertical(c, parent)
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }
        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            val bottom = mBounds.bottom + child.translationY.roundToInt()
            val top = bottom - mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(canvas)
        }
        canvas.restore()
    }

    override fun setDrawable(drawable: Drawable) {
        mDivider = drawable
    }
}