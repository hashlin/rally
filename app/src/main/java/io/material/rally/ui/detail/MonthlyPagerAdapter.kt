package io.material.rally.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.material.rally.data.model.MonthlyItem

/**
 * Created by Chan Myae Aung on 8/4/19.
 */
class MonthlyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(
    fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
  override fun getItem(position: Int): Fragment {
    return MonthlyFragment.newInstance(position)
  }

  override fun getCount(): Int {
    return 12
  }
}