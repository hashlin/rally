package io.material.rally.ui

import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.material.rally.R

/**
 * Created by Chan Myae Aung on 7/30/19.
 */
class RallyPagerAdapter(
  fragmentManager: FragmentManager,
  private val tabs: List<TabUiModel>
) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

  override fun getItem(position: Int): Fragment {
    return OverviewFragment()
  }

  override fun getCount(): Int {
    return tabs.size
  }
}

fun generateTabs(): List<TabUiModel> {
  return listOf(
      TabUiModel("Overview",R.drawable.ic_overview),
      TabUiModel("Accounts", R.drawable.ic_attach_money),
      TabUiModel("Bills", R.drawable.ic_money_off),
      TabUiModel("Overview", android.R.drawable.ic_delete),
      TabUiModel("Setting", R.drawable.ic_settings)
  )
}

data class TabUiModel(val name: String, @DrawableRes val icon: Int)