package io.material.rally.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.material.rally.R
import io.material.rally.extension.inflate

/**
 * Created by Chan Myae Aung on 8/29/19.
 */
class InfoFragment : BottomSheetDialogFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.sheet_info,container,false)
  }
}