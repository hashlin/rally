package io.material.rally.ui.overview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.material.rally.R
import io.material.rally.ui.overview.adapter.Account
import io.material.rally.ui.overview.adapter.AccountOverviewAdapter
import io.material.rally.ui.overview.adapter.Bill
import io.material.rally.ui.overview.adapter.BillAdapter
import io.material.rally.ui.overview.adapter.Budget
import io.material.rally.ui.overview.adapter.BudgetAdapter
import io.material.rally.ui.widgets.RallyAlert
import kotlinx.android.synthetic.main.fragment_overview.content
import kotlinx.android.synthetic.main.layout_account_overview.rv_account_overview
import kotlinx.android.synthetic.main.layout_bill_overview.rv_bill_overview
import kotlinx.android.synthetic.main.layout_budget_overview.rv_budget_overview

/**
 * Created by Chan Myae Aung on 7/30/19.
 */
class OverviewFragment : Fragment() {

  private val accountAdapter by lazy { AccountOverviewAdapter() }
  private val billAdapter by lazy { BillAdapter() }
  private val budgetAdapter by lazy { BudgetAdapter() }

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
    return inflater.inflate(R.layout.fragment_overview, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    setUpAccountRecyclerView()
    setUpBillRecyclerView()
    setUpBudgetRecyclerView()

    if (savedInstanceState == null) runEnterAnimation()
  }

  private fun setUpAccountRecyclerView() {
    rv_account_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(decoration)
      adapter = accountAdapter
    }
    accountAdapter.submitList(listOf(Account(""), Account(""), Account("")))

  }

  private fun setUpBillRecyclerView() {
    rv_bill_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(decoration)
      adapter = billAdapter
    }
    billAdapter.submitList(listOf(Bill(""), Bill(""), Bill("")))
  }

  private fun setUpBudgetRecyclerView() {
    rv_budget_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(decoration)
      adapter = budgetAdapter
    }
    budgetAdapter.submitList(listOf(Budget(""), Budget(""), Budget("")))
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
                try {
                  if (i == content.childCount - 1) showAlert()
                } catch (e: Exception) {
                  e.printStackTrace()
                }
              }
            })
            .start()
      }
    }
  }

}
