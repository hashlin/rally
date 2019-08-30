package io.material.rally.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.AutoTransition
import androidx.transition.Slide
import androidx.transition.TransitionManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.button.MaterialButton
import io.material.rally.R
import io.material.rally.ui.viewpager.SwipeControllableViewPager
import kotlinx.android.synthetic.main.layout_rally_tab.view.cl
import kotlinx.android.synthetic.main.layout_rally_tab.view.flow
import kotlinx.android.synthetic.main.layout_rally_tab.view.image1
import kotlinx.android.synthetic.main.layout_rally_tab.view.image2
import kotlinx.android.synthetic.main.layout_rally_tab.view.image3
import kotlinx.android.synthetic.main.layout_rally_tab.view.image4
import kotlinx.android.synthetic.main.layout_rally_tab.view.image5
import kotlinx.android.synthetic.main.layout_rally_tab.view.textView


/**
 * Created by lin min phyo on 2019-08-12.
 */

class RallyTab @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  var viewPager: SwipeControllableViewPager? = null
  var previousClickedPosition = 0
  var lastClickedPosition = 0

  private val transition by lazy {
    AutoTransition().apply {
      excludeTarget(textView, true)
    }
  }

  init {
    View.inflate(context, R.layout.layout_rally_tab, this)

    val slideInRight = Slide(Gravity.RIGHT)
    slideInRight.duration = 1000
    slideInRight.startDelay = 0

    // textView..addTransition(slideInRight)

    image1.setOnClickListener {
      viewPager?.setCurrentItem(0, false)
    }

    image2.setOnClickListener {
      viewPager?.setCurrentItem(1, false)
    }

    image3.setOnClickListener {
      viewPager?.setCurrentItem(2, false)
    }

    image4.setOnClickListener {
      viewPager?.setCurrentItem(3, false)
    }

    image5.setOnClickListener {
      viewPager?.setCurrentItem(4, false)

    }

  }

  val tabNames = listOf("Overview", "Accounts", "Bills", "Budgets", "Settings")

  fun clickedItem(position: Int) {
    TransitionManager.beginDelayedTransition(cl, transition)
    previousClickedPosition = lastClickedPosition
    lastClickedPosition = position


    if (lastClickedPosition != previousClickedPosition) {
      textView.alpha = 0f

      val refs = flow.referencedIds.toMutableList()
      refs.remove(R.id.textView)
      refs.filterIndexed { index, viewId -> index != position }
          .forEach {
            findViewById<MaterialButton>(it).iconTint =
              ColorStateList.valueOf(
                  fetchColor(io.material.design_system.R.attr.colorPrimaryVariant)
              )
          }


      findViewById<MaterialButton>(refs[position]).iconTint =
        ColorStateList.valueOf(fetchColor(io.material.design_system.R.attr.colorPrimary))
      findViewById<TextView>(R.id.textView).setTextColor(
          fetchColor(io.material.design_system.R.attr.colorPrimary)
      )


      refs.add(position + 1, R.id.textView)

      flow.referencedIds = refs.toIntArray()
      requestLayout()


      textView.text = tabNames[position]
      val alpha = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f)
      alpha.startDelay = alpha.duration / 2

      if (previousClickedPosition < lastClickedPosition) {
        val slideInX = textView.width - image1.width / 3 - image1.paddingStart
        val slide = ObjectAnimator.ofFloat(
            textView, "translationX", slideInX.toFloat(), 0f
        )
        val set = AnimatorSet()

        set.playTogether(alpha, slide)
        set.start()
      } else {
        alpha.start()
      }
    }

  }

  private fun fetchColor(colorAttr: Int): Int {
    val typedValue = TypedValue()

    val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(colorAttr))
    val color = a.getColor(0, 0)

    a.recycle()

    return color
  }

  fun setUpWithViewPager(
    viewPager: SwipeControllableViewPager,
    allowSwipe: Boolean
  ) {
    this.viewPager = viewPager
    this.viewPager?.swipeEnabled = allowSwipe

    this.viewPager?.addOnPageChangeListener(object : OnPageChangeListener {
      override fun onPageScrollStateChanged(state: Int) {

      }

      override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
      ) {

      }

      override fun onPageSelected(position: Int) {
        clickedItem(position)
      }
    })
  }


}