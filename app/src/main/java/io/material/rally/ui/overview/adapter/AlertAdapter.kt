package io.material.rally.ui.overview.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.material.rally.R
import io.material.rally.data.model.Alert
import io.material.rally.extension.inflate

/**
 * Created by lin min phyo on 2019-08-24.
 */

class AlertAdapter : ListAdapter<Alert, AlertViewHolder>(object :
    DiffUtil.ItemCallback<Alert>() {
  override fun areItemsTheSame(
    oldItem: Alert,
    newItem: Alert
  ): Boolean {
    return oldItem.alert == newItem.alert
  }

  override fun areContentsTheSame(
    oldItem: Alert,
    newItem: Alert
  ): Boolean {
    return oldItem == newItem
  }

}) {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): AlertViewHolder {
    return AlertViewHolder(parent.inflate(R.layout.item_alert))
  }

  override fun onBindViewHolder(
    holder: AlertViewHolder,
    position: Int
  ) {
    holder.bind(getItem(position))
  }
}

class AlertViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  private val alert: TextView = view.findViewById(R.id.tv_alert)
  private val image: ImageView = view.findViewById(R.id.iv_icon)
  fun bind(model: Alert) {
    alert.text = model.alert
    image.setImageResource(model.drawableRes)
  }
}