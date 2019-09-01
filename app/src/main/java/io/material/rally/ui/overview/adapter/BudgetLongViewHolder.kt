package io.material.rally.ui.overview.adapter

import android.content.res.ColorStateList
import android.graphics.drawable.LayerDrawable
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import io.material.design_system.R as designR
import io.material.rally.data.model.Budget
import android.R.attr.data
import android.content.res.TypedArray
import android.util.TypedValue
import android.content.Context
import io.material.rally.R
import io.material.rally.R.attr
import io.material.rally.ui.detail.DetailActivity

/**
 * Created by lin min phyo on 2019-08-21.
 */

class BudgetLongViewHolder(val view: View) : BudgetViewHolder(view) {
  private val barView: ProgressBar = view.findViewById(R.id.bar)
  private val tvName: TextView = view.findViewById(R.id.tvName)
  private val tvUsed: TextView = view.findViewById(R.id.tvUsed)
  private val tvTotal: TextView = view.findViewById(R.id.tvTotal)
  private val tvLeftAmount: TextView = view.findViewById(R.id.tvLeft)

  override fun bind(model: Budget) {
    tvName.text = model.name
    tvUsed.text = model.desc
//    tvTotal.text = model.total.toMoneyFormatted()
    tvLeftAmount.text = model.left.toString()
    barView.progress = ((model.spend / model.total) * 100).toInt()
    barView.progressTintList =
        ColorStateList.valueOf(ContextCompat.getColor(itemView.context, model.color))
    barView.progressBackgroundTintList =
      ColorStateList.valueOf(fetchPrimaryColor(itemView.context))

    barView.max = 100

    val progressBarDrawable = barView.progressDrawable as LayerDrawable
    val backgroundDrawable = progressBarDrawable.getDrawable(0)
    val progressDrawable = progressBarDrawable.getDrawable(1)

//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//
//      backgroundDrawable.colorFilter = BlendModeColorFilter(
//          ContextCompat.getColor(itemView.context, R.color.colorPrimary),
//          BlendMode.SRC_IN
//      )
//      progressDrawable.colorFilter = BlendModeColorFilter(
//          ContextCompat.getColor(itemView.context, model.color), BlendMode.SRC_IN
//      )
//    } else {
//      backgroundDrawable.setColorFilter(
//          ContextCompat.getColor(itemView.context, R.color.colorPrimary), SRC_IN
//      )
//      progressDrawable.setColorFilter(
//          ContextCompat.getColor(itemView.context, model.color), SRC_IN
//      )
//    }
    view.setOnClickListener {
      DetailActivity.start(view.context, view, model.color)
    }
  }

  private fun fetchPrimaryColor(context : Context): Int {
    val typedValue = TypedValue()

    val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(attr.colorSecondaryVariant))
    val color = a.getColor(0, 0)

    a.recycle()

    return color
  }
}
