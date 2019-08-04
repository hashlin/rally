package com.example.rally_scrollable_tab

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Chan Myae Aung on 8/4/19.
 */
//click listener
//integration with viewpager
class RallyScrollableTab : RecyclerView {

  private val tabAdapter by lazy { TabAdapter(style = tabTextStyle) }
  private var selectedColor = Color.WHITE
  private var unSelectedColor = Color.GRAY
  private var tabTextStyle = TabStyle(R.style.TabTextStyle)

  constructor(
    context: Context,
    attrs: AttributeSet?
  ) : super(context, attrs) {
    init(attrs)
  }

  constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int
  ) : super(context, attrs, defStyle) {
    init(attrs)
  }

  private fun init(set: AttributeSet?) {
    initAttributes(set)

    layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
    setHasFixedSize(true)
    adapter = tabAdapter

    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(this)

    createPagerStyle()

    addOnScrollListener(object : OnScrollListener() {
      override fun onScrolled(
        recyclerView: RecyclerView,
        dx: Int,
        dy: Int
      ) {
        super.onScrolled(recyclerView, dx, dy)
        post {
          (0 until childCount).forEach {
            val child = getChildAt(it)
            val childCenterX = (child.left + child.right) / 2
            val scaleValue =
              Util.getGaussianScale(childCenterX, 1f, 1f, 150.toDouble(), left, right)
//            child.scaleX = scaleValue
//            child.scaleY = scaleValue
            colorView(child, scaleValue)
          }
        }
      }
    })
  }

  private fun initAttributes(set: AttributeSet?) {
    val ta = context.obtainStyledAttributes(set, R.styleable.RallyScrollableTab)
    selectedColor = ta.getColor(R.styleable.RallyScrollableTab_selectedColor, Color.WHITE)
    unSelectedColor = ta.getColor(R.styleable.RallyScrollableTab_unSelectedColor, Color.GRAY)
    tabTextStyle =
      TabStyle(ta.getResourceId(R.styleable.RallyScrollableTab_tabTextStyle, R.style.TabTextStyle))
    ta.recycle()

  }

  private fun createPagerStyle() {
    //add padding and set clipToPadding false so other tab items are also visible at left,right edge screen
    clipToPadding = false
    val halfSWidth = context.resources.displayMetrics.widthPixels / 2
    val padding = halfSWidth / 2
    setPadding(padding, 0, padding, 0)
  }

  fun addTabs(list: List<String>) {
    tabAdapter.addAll(list)
  }

  fun addOnTabListener(listener: (position: Int) -> Unit) {
    tabAdapter.setListener(listener)
  }

  fun setUpWithViewPager() {

  }

  private fun colorView(
    child: View,
    scaleValue: Float
  ) {
    val percent = (scaleValue - 1) / 1f
    val color = ArgbEvaluator().evaluate(percent, unSelectedColor, selectedColor) as Int
    child.findViewById<TextView>(R.id.tvTab)
        .setTextColor(color)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    tabAdapter.setListener(null)
  }
}

data class TabStyle(@StyleRes val tabTextStyle: Int)
