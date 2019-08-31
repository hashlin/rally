package io.material.rally.ui.overview.adapter

import android.view.View
import android.widget.TextView
import io.material.rally.R
import io.material.rally.data.model.Budget
import io.material.rally.ui.detail.DetailActivity
import io.material.rally_line_indicator.RallyVerticalBar
import io.material.rally_line_indicator.RallyVerticalBarData

/**
 * Created by lin min phyo on 2019-08-21.
 */
class BudgetShortViewHolder(val view: View) : BudgetViewHolder(view) {
  private val barView: RallyVerticalBar = view.findViewById(R.id.bar)
  private val tvName: TextView = view.findViewById(R.id.tvName)
  private val tvUsed: TextView = view.findViewById(R.id.tvUsed)
  private val tvTotal: TextView = view.findViewById(R.id.tvTotal)
  private val tvLeftAmount: TextView = view.findViewById(R.id.tvLeft)

  override fun bind(model: Budget) {
    tvName.text = model.name
    tvUsed.text = model.desc
//    tvTotal.text = model.total.toMoneyFormatted()
    tvLeftAmount.text = model.left.toString()
    barView.renderData(RallyVerticalBarData(model.spend, model.total, model.color))

    view.setOnClickListener {
      DetailActivity.start(view.context, view, model.color)
    }
  }
}