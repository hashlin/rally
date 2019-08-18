package io.material.rally.ui.overview.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.material.rally.R
import io.material.rally.data.model.Budget
import io.material.rally.extension.inflate
import io.material.rally_line_indicator.RallyVerticalBar
import io.material.rally_line_indicator.RallyVerticalBarData

/**
 * Created by Chan Myae Aung on 7/31/19.
 */
class BudgetAdapter : ListAdapter<Budget, BudgetViewHolder>(object : DiffUtil.ItemCallback<Budget>() {
  override fun areItemsTheSame(
    oldItem: Budget,
    newItem: Budget
  ): Boolean {
    return oldItem.name == newItem.name
  }

  override fun areContentsTheSame(
    oldItem: Budget,
    newItem: Budget
  ): Boolean {
    return oldItem == newItem
  }

}) {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): BudgetViewHolder {
    return BudgetViewHolder(parent.inflate(R.layout.item_budget))
  }

  override fun onBindViewHolder(
    holder: BudgetViewHolder,
    position: Int
  ) {
    holder.bind(getItem(position))
  }
}

class BudgetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  private val barView: RallyVerticalBar = view.findViewById(R.id.bar)
  private val tvName:TextView = view.findViewById(R.id.tvName)
  private val tvDesc:TextView = view.findViewById(R.id.tvDesc)
  private val tvLeftAmount:TextView = view.findViewById(R.id.tvLeft)

  fun bind(model: Budget) {
    tvName.text = model.name
    tvDesc.text = model.desc
    tvLeftAmount.text = model.left.toString()
    barView.renderData(RallyVerticalBarData(model.spend,model.total,model.color))
  }
}
