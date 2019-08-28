package io.material.rally.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.material.rally.R
import io.material.rally.data.DataProvider
import io.material.rally.extension.getRallyItemDecoration
import kotlinx.android.synthetic.main.fragment_monthly.rv_monthly

/**
 * Created by Chan Myae Aung on 8/4/19.
 */
class MonthlyFragment : Fragment() {

  private val monthlyAdapter by lazy { MonthlyAdapter() }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_monthly, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    setUpRecyclerView()
  }

  private fun setUpRecyclerView() {
    rv_monthly.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(getRallyItemDecoration())
      adapter = monthlyAdapter
    }
    monthlyAdapter.submitList(DataProvider.monthlyItems(arguments?.getInt(KEY_MONTH) ?: 1))
  }

  companion object {
    private const val KEY_MONTH = "key-month"
    fun newInstance(month: Int): MonthlyFragment {
      return MonthlyFragment().apply {
        arguments = Bundle().apply { putInt(KEY_MONTH, month) }
      }
    }
  }
}
