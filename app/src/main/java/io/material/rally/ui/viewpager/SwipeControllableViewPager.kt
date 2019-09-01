package io.material.rally.ui.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.Interpolator
import android.widget.OverScroller
import android.widget.Scroller
import androidx.core.widget.ScrollerCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.viewpager.widget.ViewPager



/**
 * Created by lin min phyo on 2019-08-30.
 */
class SwipeControllableViewPager constructor(
  context: Context,
  attrs: AttributeSet
) : ViewPager(context, attrs) {

  var swipeEnabled  = false

  init {
    val scroller = ViewPager::class.java.getDeclaredField("mScroller")
    scroller.isAccessible = true
    scroller.set(this , FastOutSlowInScroller(context , FastOutSlowInInterpolator()))
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    return if (this.swipeEnabled) {
      super.onTouchEvent(event)
    } else false

  }

  override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
    return if (this.swipeEnabled) {
      super.onInterceptTouchEvent(event)
    } else false

  }
}

class FastOutSlowInScroller(
  context: Context,
  interpolator: Interpolator
) : Scroller(context, interpolator) {

  override fun startScroll(
    startX: Int,
    startY: Int,
    dx: Int,
    dy: Int,
    duration: Int
  ) {
    super.startScroll(startX, startY, dx, dy, 300)
  }

}
