package io.material.rally.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import io.material.rally.R

/**
 * Created by Chan Myae Aung on 7/31/19.
 */
class RallyAlert : FrameLayout {

  constructor(context: Context) : super(context) {
    init()
  }

  constructor(
    context: Context,
    attrs: AttributeSet?
  ) : super(context, attrs) {
    init()
  }

  constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
  ) : super(context, attrs, defStyleAttr) {
    init()
  }

  private fun init() {
    val view = LayoutInflater.from(context)
        .inflate(R.layout.layout_alert, this, false)
    addView(view)
  }

  class Builder {

    private var message = ""
    private var delay = 100L
    private var duration = 300L

    fun message(message: String): Builder {
      this.message = message
      return this
    }

    fun delay(delay: Long): Builder {
      this.delay = delay
      return this
    }

    fun duration(duration: Long): Builder {
      this.duration = duration
      return this
    }

    fun show(
      parent: ViewGroup,
      positionToShow: Int
    ) {

      val frameLayout = FrameLayout(parent.context).apply {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
      }
      //to ignore animateLayoutChange attribute from parent for this view
      parent.addView(frameLayout, positionToShow)

      val alert = RallyAlert(parent.context)
      frameLayout.addView(alert, positionToShow)

      alert.apply {
        alpha = 0f
        post {
          animate()
              .setStartDelay(delay)
              .setDuration(duration)
              .alpha(1f)
              .start()
        }
      }
    }
  }
}
