package io.material.rally.ui.overview.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.material.rally.R
import io.material.rally.ui.TestActivity
import io.material.rally.ui.detail.DetailActivity
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

class AccountOverviewViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

  fun bind(model: Account) {
    view.setOnClickListener {
      val intent = Intent(view.context, TestActivity::class.java)
      val pair = Pair(view.findViewById<View>(R.id.shareView), "DetailView")
      val options =
        ActivityOptionsCompat.makeSceneTransitionAnimation(view.context as AppCompatActivity, pair)
      view.context.startActivity(intent,options.toBundle())
    }
  }
}

data class Account(val name: String)