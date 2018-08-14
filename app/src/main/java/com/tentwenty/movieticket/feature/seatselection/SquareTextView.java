package com.tentwenty.movieticket.feature.seatselection;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

import com.tentwenty.movieticket.R;


public class SquareTextView extends AppCompatTextView {

    private int mTextType = SeatSelectionActivity.SEAT_AVAILABLE;

    public SquareTextView(Context context) {
        super(context);
        init(null);
    }

    public SquareTextView(Context context, int textType) {
        super(context);
        mTextType = textType;
        init(null);
    }

    public SquareTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SquareTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.text_bg));
        setAlphaOnTextColor();
        setText(getText());
        setTypeface(Typeface.DEFAULT_BOLD);
        setGravity(Gravity.CENTER);
    }

    void setAlphaOnTextColor() {
        switch (mTextType) {
            case SeatSelectionActivity.SEAT_AVAILABLE:
                setTextColor(ContextCompat.getColor(getContext(), android.R.color.darker_gray));
                setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.text_bg));
                break;
            case SeatSelectionActivity.SEAT_UNAVAILABLE:
                setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.text_bg_unavailable));

                break;
            case SeatSelectionActivity.EMPTY_SEAT_PASSAGE:
                setTextColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
                setBackgroundColor(ContextCompat.getColor(getContext(),android.R.color.transparent));
                break;
            case SeatSelectionActivity.SEAT_SELECTED:
                setTextColor(ContextCompat.getColor(getContext(),  android.R.color.white));
                setBackgroundColor(ContextCompat.getColor(getContext(),android.R.color.holo_green_dark));
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = this.getMeasuredWidth();
        int height = this.getMeasuredHeight();
        int size = Math.max(width, height);
        int widthSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        int heightSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        super.onMeasure(widthSpec, heightSpec);

    }

    public void setTextType(int mTextType) {
        this.mTextType = mTextType;
        requestLayout();
    }
}