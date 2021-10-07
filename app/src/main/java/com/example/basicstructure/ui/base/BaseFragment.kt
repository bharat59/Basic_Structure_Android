package com.example.basicstructure.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.basicstructure.util.hideKeyboard

/**
 * Created by Bharat.
 */
abstract class BaseFragment : Fragment() {

    var mBaseActivity : BaseActivity? = null
    val mPreferenceProvider : PreferenceProvider?
        get() = mBaseActivity?.mPreferenceProvider

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity)
            mBaseActivity = context
    }

    override fun onDestroyView() {
        activity?.hideKeyboard()
        super.onDestroyView()
    }
}