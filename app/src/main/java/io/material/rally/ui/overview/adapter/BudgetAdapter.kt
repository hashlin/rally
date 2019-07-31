package io.material.rally.ui.overview.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.material.rally.R
import io.material.rally.ui.extension.inflate

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

  fun bind(model: Budget) {

  }
}

data class Budget(val name: String)