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
class BillAdapter : ListAdapter<Bill, BillViewHolder>(object : DiffUtil.ItemCallback<Bill>() {
  override fun areItemsTheSame(
    oldItem: Bill,
    newItem: Bill
  ): Boolean {
    return oldItem.name == newItem.name
  }

  override fun areContentsTheSame(
    oldItem: Bill,
    newItem: Bill
  ): Boolean {
    return oldItem == newItem
  }

}) {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): BillViewHolder {
    return BillViewHolder(parent.inflate(R.layout.item_bill))
  }

  override fun onBindViewHolder(
    holder: BillViewHolder,
    position: Int
  ) {
    holder.bind(getItem(position))
  }
}

class BillViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  fun bind(model: Bill) {

  }
}

data class Bill(val name: String)