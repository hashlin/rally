package io.material.rally_pie

import android.content.Context
import android.graphics.Canvas
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
    private var rallyPieFinalRenderData = listOf<RallyPieRenderData>()

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

    private val STROKE = 20f
    private var rect = RectF()
    private var chartRadius = 500f

    private val paint by lazy {
        Paint().apply {
            strokeWidth = STROKE
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)

        chartRadius = (width / 2).toFloat() - paddingStart - paddingEnd - STROKE - STROKE

        rect.set(
            0f + STROKE + paddingLeft,
            0f + STROKE + paddingTop,
            width - STROKE - paddingRight,
            height - STROKE - paddingRight
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        canvas?.apply {
            rallyPieFinalRenderData.forEachIndexed { index, it ->
                paint.color = it.color
                drawArc(
                    rect,
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
        return rallyPieFinalRenderData
    }

    override fun startAnimation(animation: Animation?) {
        if (animation is RallyPieAnimation) {
            animation.addData(rallyPieFinalRenderData)
        }
        super.startAnimation(animation)
    }

    fun setPieData(pieData: RallyPieData, animation: RallyPieAnimation? = null) {
        val totalPortionValues = pieData.portions.sumByDouble { it.value.toDouble() }.toFloat()
        rallyPieFinalRenderData = pieData.portions.toPoints(totalPortionValues)
        if (animation != null) {
            animation.addData(rallyPieFinalRenderData)
            this.startAnimation(animation)
        } else {
            rallyPieProgressRenderData = rallyPieFinalRenderData
        }
    }


}