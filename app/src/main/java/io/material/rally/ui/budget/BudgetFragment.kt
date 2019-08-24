package io.material.rally.ui.budget

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import io.material.design_system.R.color

import io.material.rally.R
import io.material.rally.data.DataProvider
import io.material.rally.extension.getRallyItemDecoration
import io.material.rally.ui.overview.adapter.BudgetAdapter
import io.material.rally_pie.RallyPieAnimation
import io.material.rally_pie.RallyPieData
import io.material.rally_pie.RallyPiePortion
import io.material.rally_pie.pxToDp
import kotlinx.android.synthetic.main.fragment_budget.rallyPie
import kotlinx.android.synthetic.main.fragment_budget.rv_budget
import kotlinx.android.synthetic.main.layout_budget_overview.rv_budget_overview

/**
 * Created by Chan Myae Aung on 8/13/19.
 */
class BudgetFragment : Fragment() {

  private lateinit var budgetAdapter : BudgetAdapter
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_budget, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    setUpPieView()
    setUpRecyclerView()
  }

//  override fun onResume() {
//    super.onResume()
//    setUpPieView()
//  }

  private fun setUpPieView() {
    val rallyPiePortions = listOf(
        RallyPiePortion(
            "A", 100f, ContextCompat.getColor(requireContext(), color.rally_blue_100)
        ),
        RallyPiePortion(
            "A", 50f, ContextCompat.getColor(requireContext(), color.rally_purple)
        ),
        RallyPiePortion(
            "A", 300f, ContextCompat.getColor(requireContext(), color.rally_blue)
        ),
        RallyPiePortion(
            "A", 100f, ContextCompat.getColor(requireContext(), color.rally_blue_700)
        ),
        RallyPiePortion(
            "A", 50f, ContextCompat.getColor(requireContext(), color.rally_purple_300)
        ),
        RallyPiePortion(
            "A", 200f, ContextCompat.getColor(requireContext(), color.rally_orange_50)
        )
    )
    val rallyPieData = RallyPieData(portions = rallyPiePortions)
    val rallyPieAnimation = RallyPieAnimation(rallyPie)
    rallyPieAnimation.duration = 600

    rallyPie.setPieData(pieData = rallyPieData, animation = rallyPieAnimation)
  }

  private fun setUpRecyclerView() {
    val isTwoLine = requireContext().pxToDp(rv_budget.measuredWidth) >= 400
    Log.i("Width" , requireContext().pxToDp(requireView().measuredWidth).toString() )
    budgetAdapter = BudgetAdapter(isTwoLine)

    rv_budget.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(getRallyItemDecoration())
      adapter = budgetAdapter
    }
    budgetAdapter.submitList(DataProvider.budgetOverView.budgets)
  }

}
