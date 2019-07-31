package io.material.rally.ui.overview.adapter

import android.view.LayoutInflater
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
class AccountOverviewAdapter : ListAdapter<Account, AccountOverviewViewHolder>(object : DiffUtil.ItemCallback<Account>() {
  override fun areItemsTheSame(
    oldItem: Account,
    newItem: Account
  ): Boolean {
    return oldItem.name == newItem.name
  }

  override fun areContentsTheSame(
    oldItem: Account,
    newItem: Account
  ): Boolean {
    return oldItem == newItem
  }

}) {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): AccountOverviewViewHolder {
    return AccountOverviewViewHolder(parent.inflate(R.layout.item_account))
  }

  override fun onBindViewHolder(
    holder: AccountOverviewViewHolder,
    position: Int
  ) {
    holder.bind(getItem(position))
  }
}

class AccountOverviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  fun bind(model: Account) {

  }
}

data class Account(val name: String)