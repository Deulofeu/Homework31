package com.example.homework31

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View


class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var counter = 0
    private var changeOperation = true
    private var radius = 0.0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.DialView).apply {
            try {
                counter = getInt(R.styleable.DialView_counter_default_state, 0)
                changeOperation = getBoolean(R.styleable.DialView_change_operation, true)
            } finally {
                recycle()
            }
        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        radius = (Math.min(width, height) / 2.0 * 0.8).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (counter < 10) {
            paint.color = Color.GREEN
        }
        if (counter in 10..19) {
            if (counter == 10) radius += 25f
            paint.color = Color.YELLOW
        }
        if (counter > 19) {
            if (counter == 20) radius += 25f
            paint.color = Color.RED
        }
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
        paint.color = Color.BLACK
        canvas.drawText(
            counter.toString(),
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            paint
        )
    }

    init {
        isClickable = true
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true

        if (changeOperation) {
            increase()
        } else {
            decrease()
        }
        invalidate()
        return true
    }

    fun change() {
        changeOperation = !changeOperation
    }

    fun increase() {
        ++counter
    }

    fun decrease() {
        --counter
    }
}