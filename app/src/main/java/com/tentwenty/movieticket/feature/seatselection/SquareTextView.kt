package com.tentwenty.movieticket.feature.seatselection

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.Gravity
import android.view.View

import com.tentwenty.movieticket.R


class SquareTextView : AppCompatTextView {

    private var mTextType = SeatSelectionActivity.SEAT_AVAILABLE

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, textType: Int) : super(context) {
        mTextType = textType
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {

        setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.text_bg))
        setAlphaOnTextColor()
        text = text
        typeface = Typeface.DEFAULT_BOLD
        gravity = Gravity.CENTER
    }

    internal fun setAlphaOnTextColor() {
        when (mTextType) {
            SeatSelectionActivity.SEAT_AVAILABLE -> {
                setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray))
                setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.text_bg))
            }
            SeatSelectionActivity.SEAT_UNAVAILABLE -> {
                setTextColor(ContextCompat.getColor(context, android.R.color.white))
                setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.text_bg_unavailable))
            }
            SeatSelectionActivity.EMPTY_SEAT_PASSAGE -> {
                setTextColor(ContextCompat.getColor(context, android.R.color.transparent))
                setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            }
            SeatSelectionActivity.SEAT_SELECTED -> {
                setTextColor(ContextCompat.getColor(context, android.R.color.white))
                setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
            }
            SeatSelectionActivity.SEAT_BOOKED -> {
                setTextColor(ContextCompat.getColor(context, android.R.color.white))
                setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.text_bg_booked))
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = this.measuredWidth
        val height = this.measuredHeight
        val size = Math.max(width, height)
        val widthSpec = View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.EXACTLY)
        super.onMeasure(widthSpec, heightSpec)

    }

    fun setTextType(mTextType: Int) {
        this.mTextType = mTextType
        requestLayout()
    }
}