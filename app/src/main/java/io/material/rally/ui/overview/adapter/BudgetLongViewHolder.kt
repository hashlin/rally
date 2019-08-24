package io.material.rally.ui.overview.adapter

import android.content.res.ColorStateList
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import io.material.rally.R
import io.material.rally.data.model.Budget

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
    barView.max = 100

    barView.progressTintList = ColorStateList(
        arrayOf(
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_active),
            intArrayOf(android.R.attr.checked),
            intArrayOf(android.R.attr.state_pressed)
        )
        ,
        intArrayOf(
            model.color,
            model.color,
            model.color,
            model.color
        )
    )
  }
}
