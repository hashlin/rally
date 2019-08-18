package io.material.rally.ui.bill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.material.design_system.R.color
import io.material.rally.R
import io.material.rally.data.DataProvider
import io.material.rally.extension.getRallyItemDecoration
import io.material.rally.ui.overview.adapter.BillAdapter
import io.material.rally_pie.RallyPieAnimation
import io.material.rally_pie.RallyPieData
import io.material.rally_pie.RallyPiePortion
import kotlinx.android.synthetic.main.fragment_bill.rallyPie
import kotlinx.android.synthetic.main.fragment_bill.rv_bill

/**
 * Created by Chan Myae Aung on 8/13/19.
 */
class BillFragment : Fragment() {

  private val billAdapter by lazy { BillAdapter() }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_bill, container, false)
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
            "A", 50f, ContextCompat.getColor(requireContext(), color.rally_yellow_300)
        ),
        RallyPiePortion(
            "A", 500f, ContextCompat.getColor(requireContext(), color.rally_orange_200)
        ),
        RallyPiePortion(
            "A", 100f, ContextCompat.getColor(requireContext(), color.rally_orange_50)
        ),
        RallyPiePortion(
            "A", 200f, ContextCompat.getColor(requireContext(), color.rally_yellow_200)
        ),
        RallyPiePortion(
            "A", 80f, ContextCompat.getColor(requireContext(), color.rally_orange_50)
        )
    )
    val rallyPieData = RallyPieData(portions = rallyPiePortions)
    val rallyPieAnimation = RallyPieAnimation(rallyPie)
    rallyPieAnimation.duration = 600

    rallyPie.setPieData(pieData = rallyPieData, animation = rallyPieAnimation)
  }

  private fun setUpRecyclerView() {
    rv_bill.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(getRallyItemDecoration())
      adapter = billAdapter
    }
    billAdapter.submitList(DataProvider.billOverView.bills)
  }

}
