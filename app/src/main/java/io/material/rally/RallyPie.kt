package io.material.rally

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * Created by lin min phyo on 2019-07-29.
 */
class RallyPie : View {

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
//    private var chartRadius = 30f

//    private val paint by lazy {
//        Paint().apply {
//            color = Color.RED
//        }
//    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

//        chartRadius = (widthMeasureSpec / 2).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // canvas?.drawCircle((x + width) / 2, (y + height) / 2, chartRadius, paint)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

}