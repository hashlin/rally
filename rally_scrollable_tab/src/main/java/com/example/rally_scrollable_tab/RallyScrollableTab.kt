package com.example.rally_scrollable_tab

import android.animation.ArgbEvaluator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

/**
 * Created by Chan Myae Aung on 8/4/19.
 */
class RallyScrollableTab : RecyclerView {

  private val tabAdapter by lazy { TabAdapter(style = tabTextStyle) }
  private var selectedColor = Color.WHITE
  private var unSelectedColor = Color.GRAY
  private var tabTextStyle = TabStyle(R.style.TabTextAppearance)
  private val layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
  private var viewPager: ViewPager? = null
  private var isRVScrolling = true
  private var listener: ((position: Int) -> Unit)? = null
  private var pageChangeListener: ((position: Int) -> Unit)? = null

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

    setLayoutManager(layoutManager)
    setHasFixedSize(true)
    adapter = tabAdapter

    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(this)

    createPagerStyle()

    setOnTouchListener { _, _ ->
      isRVScrolling = true
      false
    }
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
        //viewPager?.scrollBy(dx*2, 0)
      }

      override fun onScrollStateChanged(
        recyclerView: RecyclerView,
        newState: Int
      ) {
        super.onScrollStateChanged(recyclerView, newState)

        if (newState == SCROLL_STATE_IDLE) {
          //need flat to disable this when viewpager is scrolling
          val child = snapHelper.findSnapView(layoutManager) ?: return
          if (isRVScrolling) viewPager?.setCurrentItem(layoutManager.getPosition(child), true)
        }
      }
    })

    tabAdapter.onTabClick {
      isRVScrolling = true
      listener?.invoke(it)
      smoothScrollToPosition(it)
      viewPager?.setCurrentItem(it, true)
    }
  }

  private fun initAttributes(set: AttributeSet?) {
    val ta = context.obtainStyledAttributes(set, R.styleable.RallyScrollableTab)
    selectedColor = ta.getColor(R.styleable.RallyScrollableTab_selectedColor, Color.WHITE)
    unSelectedColor = ta.getColor(R.styleable.RallyScrollableTab_unSelectedColor, Color.GRAY)
    tabTextStyle =
      TabStyle(ta.getResourceId(R.styleable.RallyScrollableTab_tabTextAppearance, R.style.TabTextAppearance))
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
    this.listener = listener
  }

  fun addOnPageChangeListener(pageChangeListener: (position: Int) -> Unit){
    this.pageChangeListener = pageChangeListener
  }

  @SuppressLint("ClickableViewAccessibility") fun setUpWithViewPager(viewPager: ViewPager) {
    if (viewPager.adapter == null) throw IllegalStateException(
        "ViewPager does not have pager adapter"
    )
    this.viewPager = viewPager

    viewPager.setOnTouchListener { _, _ ->
      isRVScrolling = false
      false
    }

    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrollStateChanged(state: Int) {}

      override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
      ) {
        //scroll with offset divided by 2 as tab item width is half of viewpager item width)
        if (!isRVScrolling)
          layoutManager.scrollToPositionWithOffset(position, -positionOffsetPixels / 2)
      }

      override fun onPageSelected(position: Int) {
        smoothScrollToPosition(position)
        pageChangeListener?.invoke(position)
      }
    })
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
    tabAdapter.onTabClick(null)
    viewPager = null
  }


}

data class TabStyle(@StyleRes val tabTextStyle: Int)
