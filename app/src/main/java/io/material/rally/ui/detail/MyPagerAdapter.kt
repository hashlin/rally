package io.material.rally.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.material.rally.ui.account.AccountFragment

/**
 * Created by Chan Myae Aung on 8/4/19.
 */
class MyPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  override fun getItem(position: Int): Fragment {
    return AccountFragment()
  }

  override fun getCount(): Int {
    return 10
  }
}