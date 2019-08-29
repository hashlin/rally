package io.material.rally.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.material.rally.R
import io.material.rally.data.DataProvider
import io.material.rally.extension.getRallyItemDecoration
import io.material.rally.extension.toUSDFormatted
import io.material.rally.ui.info.InfoFragment
import io.material.rally.ui.overview.adapter.AccountOverviewAdapter
import io.material.rally_pie.RallyPieAnimation
import io.material.rally_pie.RallyPieData
import io.material.rally_pie.RallyPiePortion
import kotlinx.android.synthetic.main.fragment_account.btn_info
import kotlinx.android.synthetic.main.fragment_account.rallyPie
import kotlinx.android.synthetic.main.fragment_account.rv_account
import kotlinx.android.synthetic.main.fragment_account.tvAmount

/**
 * Created by Chan Myae Aung on 8/1/19.
 */

class AccountFragment : Fragment() {

  private val accountAdapter by lazy { AccountOverviewAdapter(isSingleLine = false) }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_account, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    setUpPieView()
    setUpRecyclerView()
    btn_info.setOnClickListener {
      val infoFragment = InfoFragment()
      infoFragment.show(childFragmentManager, "AccountInfo")
    }
  }

  private fun setUpPieView() {

    tvAmount.text = DataProvider.accountOverView.total.toUSDFormatted()

    val rallyPiePortions = DataProvider.accountOverView.accounts.map {
      RallyPiePortion(
          it.name, it.amount, ContextCompat.getColor(requireContext(), it.color)
      )
    }
        .toList()

    val rallyPieData = RallyPieData(portions = rallyPiePortions)
    val rallyPieAnimation = RallyPieAnimation(rallyPie)
    rallyPieAnimation.duration = 600

    rallyPie.setPieData(pieData = rallyPieData, animation = rallyPieAnimation)
  }

  private fun setUpRecyclerView() {
    rv_account.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(getRallyItemDecoration())
      adapter = accountAdapter
    }
    accountAdapter.submitList(DataProvider.accountOverView.accounts)
  }

}
