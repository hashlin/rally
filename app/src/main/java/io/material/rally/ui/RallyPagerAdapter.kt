package io.material.rally.ui

import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.material.rally.R
import io.material.rally.ui.account.AccountFragment
import io.material.rally.ui.overview.OverviewFragment
import io.material.rally.ui.settings.CustomViewFragment

/**
 * Created by Chan Myae Aung on 7/30/19.
 */
class RallyPagerAdapter(
  fragmentActivity: FragmentActivity,
  private val tabs: List<TabUiModel>
) : FragmentStateAdapter(
    fragmentActivity
) {
  override fun getItemCount(): Int {
    return tabs.size
  }

  override fun createFragment(position: Int): Fragment {
    return when (position) {
      0 -> OverviewFragment()
      1 -> AccountFragment()
      2 -> OverviewFragment()
      3 -> OverviewFragment()
      4 -> CustomViewFragment()
      else -> OverviewFragment()
    }
  }
}

fun generateTabs(): List<TabUiModel> {
  return listOf(
      TabUiModel("Overview", R.drawable.ic_overview),
      TabUiModel("Accounts", R.drawable.ic_attach_money),
      TabUiModel("Bills", R.drawable.ic_money_off),
      TabUiModel("Overview", R.drawable.ic_budget),
      TabUiModel("Setting", R.drawable.ic_settings)
  )
}

data class TabUiModel(val name: String, @DrawableRes val icon: Int)