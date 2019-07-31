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

    var rallyPieProgressRenderData = listOf<RallyPieRenderData>()
    private var rallyPieRenderData = listOf<RallyPieRenderData>()

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

    private var chartRadius = 500f

    private val paint by lazy {
        Paint().apply {
            color = Color.RED
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

        canvas?.apply {
            rallyPieRenderData.forEachIndexed { index, it ->
                paint.color = it.color
                drawArc(
                    rect1,
                    rallyPieProgressRenderData[index].startAngle,
                    rallyPieProgressRenderData[index].sweepAngle,
                    false,
                    paint
                )

            }

        }

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    fun getRallyPieRenderData(): List<RallyPieRenderData> {
        return rallyPieRenderData
    }

    override fun startAnimation(animation: Animation?) {
        if (animation is RallyPieAnimation) {
            animation.addData(rallyPieRenderData)
        }
        super.startAnimation(animation)
    }

    fun setPieData(pieData: RallyPieData) {
        val totalPortionValues = pieData.portions.sumByDouble { it.value.toDouble() }.toFloat()
        rallyPieRenderData = pieData.portions.toPoints(totalPortionValues)
    }


}