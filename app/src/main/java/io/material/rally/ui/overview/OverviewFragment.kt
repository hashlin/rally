package io.material.rally.ui.overview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.material.design_system.R.color
import io.material.rally.R
import io.material.rally.data.DataProvider
import io.material.rally.extension.getRallyItemDecoration
import io.material.rally.extension.toMoneyFormatted
import io.material.rally.ui.overview.adapter.AccountOverviewAdapter
import io.material.rally.ui.overview.adapter.BillAdapter
import io.material.rally.ui.overview.adapter.BudgetAdapter
import io.material.rally.ui.widgets.RallyAlert
import io.material.rally_line_indicator.data.RallyLineIndicatorData
import io.material.rally_line_indicator.data.RallyLineIndicatorPortion
import io.material.rally_pie.pxToDp
import kotlinx.android.synthetic.main.fragment_overview.content
import kotlinx.android.synthetic.main.layout_account_overview.account_line_indicator
import kotlinx.android.synthetic.main.layout_account_overview.rv_account_overview
import kotlinx.android.synthetic.main.layout_account_overview.tv_account_amount
import kotlinx.android.synthetic.main.layout_bill_overview.bill_line_indicator
import kotlinx.android.synthetic.main.layout_bill_overview.rv_bill_overview
import kotlinx.android.synthetic.main.layout_bill_overview.tv_bill_amount
import kotlinx.android.synthetic.main.layout_budget_overview.budget_line_indicator
import kotlinx.android.synthetic.main.layout_budget_overview.rv_budget_overview
import kotlinx.android.synthetic.main.layout_budget_overview.tv_budget_amount

/**
 * Created by Chan Myae Aung on 7/30/19.
 */
class OverviewFragment : Fragment() {

  private val accountAdapter by lazy { AccountOverviewAdapter(isSingleLine = false) }
  private val billAdapter by lazy { BillAdapter() }
  private lateinit var budgetAdapter : BudgetAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_overview, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    setUpAccountRecyclerView()
    setUpBillOverview()
    setUpBillRecyclerView()
    setUpBudgetOverview()
    setUpBudgetRecyclerView()

    if (savedInstanceState == null) runEnterAnimation()
  }

  private fun setUpAccountOverview(lineIndicatorData: RallyLineIndicatorData) {
    account_line_indicator.setData(lineIndicatorData)
    tv_account_amount.text = DataProvider.accountOverView.total.toMoneyFormatted()
  }

  private fun setUpAccountRecyclerView() {
    rv_account_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(getRallyItemDecoration())
      adapter = accountAdapter
    }
    accountAdapter.submitList(DataProvider.accountOverView.accounts.take(3))

    val portions = DataProvider.accountOverView.accounts.take(3)
        .map {
          RallyLineIndicatorPortion(
              name = it.name,
              value = it.amount,
              colorInt = ContextCompat.getColor(requireContext(), it.color)
          )
        }
    setUpAccountOverview(RallyLineIndicatorData(portions = portions))
  }

  private fun setUpBillOverview() {
    val rallyLineIndicatorPortions = listOf(
        RallyLineIndicatorPortion(
            "A", 100f, ContextCompat.getColor(requireContext(), color.rally_green_500)
        ),
        RallyLineIndicatorPortion(
            "A", 500f, ContextCompat.getColor(requireContext(), color.rally_green_700)
        ),
        RallyLineIndicatorPortion(
            "A", 300f, ContextCompat.getColor(requireContext(), color.rally_green_300)
        )
    )
    bill_line_indicator.setData(RallyLineIndicatorData(rallyLineIndicatorPortions))
    tv_bill_amount.text = DataProvider.billOverView.total.toMoneyFormatted()
  }

  private fun setUpBillRecyclerView() {
    rv_bill_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(getRallyItemDecoration())
      adapter = billAdapter
    }
    billAdapter.submitList(DataProvider.billOverView.bills.take(3))
  }

  private fun setUpBudgetOverview() {
    val rallyLineIndicatorPortions = listOf(
        RallyLineIndicatorPortion(
            "A", 100f, ContextCompat.getColor(requireContext(), color.rally_green_500)
        ),
        RallyLineIndicatorPortion(
            "A", 500f, ContextCompat.getColor(requireContext(), color.rally_green_700)
        ),
        RallyLineIndicatorPortion(
            "A", 300f, ContextCompat.getColor(requireContext(), color.rally_green_300)
        )
    )
    budget_line_indicator.setData(RallyLineIndicatorData(rallyLineIndicatorPortions))
    tv_budget_amount.text = DataProvider.budgetOverView.total
  }

  private fun setUpBudgetRecyclerView() {

    val isTwoLine = requireContext().resources.configuration.smallestScreenWidthDp >= 600
    budgetAdapter = BudgetAdapter(isTwoLine)

    rv_budget_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(getRallyItemDecoration())
      adapter = budgetAdapter
    }
    budgetAdapter.submitList(DataProvider.budgetOverView.budgets.take(3))
  }

  private fun showAlert() {
    RallyAlert.Builder()
        .show(parent = content, positionToShow = 0)
  }

  private fun runEnterAnimation() {
    content.post {
      var duration = 300L
      for (i in 0 until content.childCount) {
        duration += 100
        val child = content.getChildAt(i)
        child.translationY += 400
        child.alpha = 0f
        child.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(duration)
            .setInterpolator(DecelerateInterpolator())
            .setListener(object : AnimatorListenerAdapter() {
              override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                //This may crash if user navigate tab before animation end
                //Bad error handling way here
                //in real world,this could be with liveData or something else
//                try {
//                  if (i == content.childCount - 1) showAlert()
//                } catch (e: Exception) {
//                  e.printStackTrace()
//                }
              }
            })
            .start()
      }

    }
  }

}

