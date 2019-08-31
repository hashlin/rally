package io.material.rally.ui

import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import io.material.rally.R
import io.material.rally.ui.account.AccountFragment
import io.material.rally.ui.bill.BillFragment
import io.material.rally.ui.budget.BudgetFragment
import io.material.rally.ui.overview.OverviewFragment
import io.material.rally.ui.settings.CustomViewFragment

/**
 * Created by Chan Myae Aung on 7/30/19.
 */
class RallyPagerAdapter(
  fm: FragmentManager,
  private val tabs: List<TabUiModel>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  override fun getItem(position: Int): Fragment {
    return when (position) {
      0 -> OverviewFragment()
      1 -> AccountFragment()
      2 -> BillFragment()
      3 -> BudgetFragment()
      4 -> CustomViewFragment()
      else -> OverviewFragment()
    }
  }

  override fun getCount(): Int {
    return tabs.size
  }

}

fun generateTabs(): List<TabUiModel> {
  return listOf(
      TabUiModel("Overview", R.drawable.ic_overview),
      TabUiModel("Accounts", R.drawable.ic_attach_money),
      TabUiModel("Bills", R.drawable.ic_money_off),
      TabUiModel("Budget", R.drawable.ic_budget),
      TabUiModel("Setting", R.drawable.ic_settings)
  )
}

data class TabUiModel(val name: String, @DrawableRes val icon: Int)