package com.example.rally_scrollable_tab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Chan Myae Aung on 8/4/19.
 */
class TabAdapter(
  private val tabs: MutableList<String> = mutableListOf(),
  private val style: TabStyle
) : RecyclerView.Adapter<TabViewHolder>() {

  private var listener: ((position: Int) -> Unit)? = null

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): TabViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_tab, parent, false)

    TextViewCompat.setTextAppearance(view.findViewById(R.id.tvTab), style.tabTextStyle)
    return TabViewHolder(view,listener)
  }

  override fun getItemCount(): Int {
    return tabs.size
  }

  override fun onBindViewHolder(
    holder: TabViewHolder,
    position: Int
  ) {
    holder.bind(tabs[position])
  }

  fun addAll(list: List<String>) {
    tabs.clear()
    tabs.addAll(list)
    notifyDataSetChanged()
  }

  fun setListener(listener: ((position: Int) -> Unit)?){
    this.listener = listener
  }

  internal fun listenTabChangeForPager(){}

}

class TabViewHolder(
  view: View,
  private val listener: ((position: Int) -> Unit)?
) : RecyclerView.ViewHolder(view) {

  private val tab: TextView = view.findViewById(R.id.tvTab)

  init {
    view.setOnClickListener {
      listener?.invoke(adapterPosition)
    }
  }

  fun bind(model: String) {
    tab.text = model
  }
}
