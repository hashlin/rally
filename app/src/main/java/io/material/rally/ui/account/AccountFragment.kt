package io.material.rally.ui.account

import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.material.design_system.R.color

import io.material.rally.R
import io.material.rally.ui.overview.adapter.Account
import io.material.rally.ui.overview.adapter.AccountOverviewAdapter
import io.material.rally_pie.RallyPieAnimation
import io.material.rally_pie.RallyPieData
import io.material.rally_pie.RallyPiePortion
import kotlinx.android.synthetic.main.fragment_account.rallyPie
import kotlinx.android.synthetic.main.fragment_account.rv_account

/**
 * Created by Chan Myae Aung on 8/1/19.
 */
class AccountFragment : Fragment() {

  private val accountAdapter by lazy { AccountOverviewAdapter() }

  private val decoration by lazy {
    DividerItemDecoration(requireContext(), RecyclerView.VERTICAL).apply {
      val divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!
      val margin = resources.getDimensionPixelSize(R.dimen.spacing_medium)
      setDrawable(InsetDrawable(divider, margin, 0, margin, 0))
    }
  }

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
  }

  private fun setUpPieView() {
    val rallyPiePortions = listOf(
        RallyPiePortion(
            "A", 100f, ContextCompat.getColor(requireContext(), color.rally_green_500)
        ),
        RallyPiePortion(
            "A", 500f, ContextCompat.getColor(requireContext(), color.rally_green_700)
        ),
        RallyPiePortion(
            "A", 300f, ContextCompat.getColor(requireContext(), color.rally_green_300)
        )
    )
    val rallyPieData = RallyPieData(portions = rallyPiePortions)
    val rallyPieAnimation = RallyPieAnimation(rallyPie)
    rallyPieAnimation.duration = 600

    rallyPie.setPieData(pieData = rallyPieData, animation = rallyPieAnimation)
  }

  private fun setUpRecyclerView() {
    rv_account.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(decoration)
      adapter = accountAdapter
    }
    accountAdapter.submitList(
        listOf(
            Account(""), Account(""), Account(""), Account(""), Account(""), Account("")
        )
    )
  }

}
