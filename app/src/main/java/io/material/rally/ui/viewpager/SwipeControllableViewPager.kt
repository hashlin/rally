package io.material.rally.ui.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by lin min phyo on 2019-08-30.
 */
class SwipeControllableViewPager constructor(
  context: Context,
  attrs: AttributeSet
) : ViewPager(context, attrs) {

  var swipeEnabled  = false

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