package io.material.rally.ui.overview.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.material.rally.R
import io.material.rally.data.model.Budget
import io.material.rally.extension.inflate

/**
 * Created by Chan Myae Aung on 7/31/19.
 */
class BudgetAdapter(private val isLargeUI: Boolean) : ListAdapter<Budget, BudgetViewHolder>(object :
    DiffUtil.ItemCallback<Budget>() {
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
    return if (isLargeUI) {
      BudgetLongViewHolder(parent.inflate(R.layout.item_budget_long))
    } else {
      BudgetShortViewHolder(parent.inflate(R.layout.item_budget_short))
    }
  }

  override fun onBindViewHolder(
    holder: BudgetViewHolder,
    position: Int
  ) {
    holder.bind(getItem(position))
  }
}


abstract class BudgetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  abstract fun bind(model: Budget)
}