package io.material.rally_pie

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import androidx.appcompat.widget.ViewUtils
import androidx.core.content.ContextCompat
import com.google.android.material.internal.ViewUtils.dpToPx
import androidx.core.content.res.TypedArrayUtils.getResourceId
import androidx.core.graphics.drawable.DrawableCompat.setTint
import android.content.res.TypedArray
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

/**
 * Created by lin min phyo on 2019-07-29.
 */
class RallyPie @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    val defStyleAttr: Int = 0,
    val defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr) {

    var rallyPieProgressRenderData = listOf<RallyPieRenderData>()
    private var rallyPieFinalRenderData = listOf<RallyPieRenderData>()

    private val STROKE = context.dpToPx(6f)
    private var rect = RectF()
    private var chartRadius = 500f
    private var centerX = 0.0f
    private var centerY = 0.0f

    private val paint by lazy {
        Paint().apply {
            strokeWidth = STROKE
            isAntiAlias = true
            style = Paint.Style.STROKE

        }
    }

    var colorPrimary : Int = 0

    init{
        // Get attrs
        colorPrimary = Color.parseColor("#2A2931")

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
        centerX = rect.centerX()
        centerY = rect.centerY()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = colorPrimary
        canvas?.drawCircle(centerX , centerY , chartRadius + STROKE , paint)

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


    override fun startAnimation(animation: Animation?) {
        if (animation is RallyPieAnimation) {
            animation.addData(rallyPieFinalRenderData)
        }
        super.startAnimation(animation)
    }

    fun setPieData(pieData: RallyPieData, animation: RallyPieAnimation? = null) {
        val totalPortionValues = pieData.maxValue ?: pieData.portions.sumByDouble { it.value.toDouble() }.toFloat()
        rallyPieFinalRenderData = pieData.portions.toPoints(totalPortionValues)
        if (animation != null) {
            animation.addData(rallyPieFinalRenderData)
            animation.interpolator = FastOutSlowInInterpolator()
            this.startAnimation(animation)
        } else {
            rallyPieProgressRenderData = rallyPieFinalRenderData
        }
    }


}