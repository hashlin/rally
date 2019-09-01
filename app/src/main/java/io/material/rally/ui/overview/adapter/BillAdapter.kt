package io.material.rally.ui.overview.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.material.rally.R
import io.material.rally.data.model.Bill
import io.material.rally.extension.inflate
import io.material.rally.extension.toMoneyFormatted
import io.material.rally.ui.detail.DetailActivity
import io.material.rally_line_indicator.RallyVerticalBar
import io.material.rally_line_indicator.RallyVerticalBarData

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

class BillViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
  private val barView: RallyVerticalBar = view.findViewById(R.id.bar)
  private val tvName: TextView = view.findViewById(R.id.tvName)
  private val tvDescription: TextView = view.findViewById(R.id.tvDesc)
  private val tvAmount : TextView = view.findViewById(R.id.tvAmount)

  fun bind(model: Bill) {
    barView.renderData(RallyVerticalBarData(100f, 100f, model.color))
    tvName.text = model.name
    tvDescription.text = model.desc
    tvAmount.text = model.amount.toMoneyFormatted()

    view.setOnClickListener {
      DetailActivity.start(view.context, view, model.color)
    }
  }
}

