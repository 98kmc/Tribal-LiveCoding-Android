package com.kmc.android_views_skeleton.utils.navigation.navigator

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kmc.android_views_skeleton.R
import com.kmc.android_views_skeleton.databinding.FragmentPresentedBottomSheetBinding


class PresentedBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPresentedBottomSheetBinding? = null
    private var showInFullScreen: Boolean = false
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPresentedBottomSheetBinding.inflate(inflater, container, false)
        binding.presentedViewContainer.clipToOutline = true
        showInFullScreen = arguments?.getBoolean("showInFullScreen") ?: false
        if (showInFullScreen) binding.presentedViewContainer.background = ContextCompat.getDrawable(
            (context as Activity),
            R.drawable.bg_bottom_sheet_shape
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomSheet =
            dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)

        bottomSheet?.let {

            val behavior = BottomSheetBehavior.from(it)
            val layoutParams = it.layoutParams
            val windowWidth = getWindowHeight().widthPixels
            val windowHeight = getWindowHeight().heightPixels
            if (layoutParams != null) {
                layoutParams.height =
                    if (showInFullScreen) windowHeight else (windowHeight * 0.95).toInt()
                layoutParams.width = (windowWidth * 0.99).toInt()
            }
            it.layoutParams = layoutParams
            behavior.isDraggable = !showInFullScreen
            behavior.state = BottomSheetBehavior.STATE_EXPANDED

            behavior.addBottomSheetCallback(object : BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        dismiss()
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    if (slideOffset < 0.3) behavior.state = BottomSheetBehavior.STATE_HIDDEN
                    //else behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            })
            it.clipToOutline = true
        }
    }

    private fun getWindowHeight(): DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics
    }

    companion object {
        fun newInstance(showInFullScreen: Boolean): PresentedBottomSheetFragment {
            val fragment = PresentedBottomSheetFragment()
            fragment.arguments = bundleOf("showInFullScreen" to showInFullScreen)
            return fragment
        }
    }
}