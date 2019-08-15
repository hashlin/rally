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
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import io.material.rally.R
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

  var viewPager: ViewPager2? = null
  var previousClickedPosition = 0
  var lastClickedPosition = 0
  var isInTransition = false

  val transition by lazy {
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

  private fun clickedItem(position: Int) {
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


      textView.text = tabNames[position]
      val alpha = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f)
      alpha.startDelay = alpha.duration / 2

      if (previousClickedPosition < lastClickedPosition) {
        val slideInX = textView.width - image1.width / 3 - image1.paddingStart
        val slide = ObjectAnimator.ofFloat(
            textView, "translationX", slideInX.toFloat(), 0f
        )
//          slide.interpolator = transition.interpolator
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

  fun setUpWithViewPager2(
    viewPager: ViewPager2,
    allowSwipe: Boolean
  ) {
    this.viewPager = viewPager
    this.viewPager?.isUserInputEnabled = allowSwipe

    viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
      override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        clickedItem(position)
      }
    })
  }
}