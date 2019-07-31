package io.material.rally_pie

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation

/**
 * Created by lin min phyo on 2019-07-29.
 */
class RallyPie : View {


    private var startAngles = listOf(
        270f,
        270f,
        270f,
        270f
    )


    private var sweepAngles = listOf(
        0f,
        0f,
        0f,
        0f
    )

    constructor(context: Context?) : super(context)


    constructor(
        context: Context?,
        attrs: AttributeSet?
    )
            : super(context, attrs)

    constructor(
        context: Context?,
        attrs: AttributeSet? = null,
        defStyleAttr: Int
    )
            : super(context, attrs, defStyleAttr)

    //    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    constructor(
//        context: Context,
//        attrs: AttributeSet? = null,
//        defStyleAttr: Int,
//        defStyleRes: Int)
//            : super(context, attrs, defStyleAttr, defStyleRes)
    private var chartRadius = 500f

    private val paint by lazy {
        Paint().apply {
            color = Color.RED
            strokeWidth = 20f
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
    }

    private val paint2 by lazy {
        Paint().apply {
            color = Color.GREEN
            strokeWidth = 20f
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
    }


    private val paint3 by lazy {
        Paint().apply {
            color = Color.MAGENTA
            strokeWidth = 20f
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
    }


    private val paint4 by lazy {
        Paint().apply {
            color = Color.BLUE
            strokeWidth = 20f
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
    }

    private var rect1 = RectF()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        chartRadius = 500f
        //chartRadius = (widthMeasureSpec / 2).toFloat()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rect1.set(
            0f + 10f,
            0f + 10f,
            width.toFloat() - 10f,
            height.toFloat() - 10f
        )
        //canvas?.drawCircle((width / 2f), ( height/ 2f) , chartRadius, paint)
        canvas?.drawArc(rect1, startAngles[0], sweepAngles[0], false, paint)
        canvas?.drawArc(rect1, startAngles[1], sweepAngles[1], false, paint2)
        canvas?.drawArc(rect1, startAngles[2], sweepAngles[2], false, paint3)
        canvas?.drawArc(rect1, startAngles[3], sweepAngles[3], false, paint4)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }


    fun getSweepAngles(): List<Float> {
        return sweepAngles
    }

    fun setSweepAngles(angles: List<Float>) {
        this.sweepAngles = angles
    }


    fun getStartAngles(): List<Float> {
        return startAngles
    }


    fun setStartAngles(angles: List<Float>) {
        this.startAngles = angles
    }


    override fun startAnimation(animation: Animation?) {
        if (animation is RallyPieAnimation) {
            animation.setStartAngles(
                listOf(
                    270f,
                    300f,
                    350f,
                    250+360f
                )
            )
            animation.setAngles(listOf(30f, 50f, 260f, 20f))
        }
        super.startAnimation(animation)
    }
}