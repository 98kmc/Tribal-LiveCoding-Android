package com.kmc.android_views_skeleton.modules.random_joke.ui.random_joke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.kmc.android_views_skeleton.R
import com.kmc.android_views_skeleton.databinding.FragmentRandomJokeBinding
import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.JokeCategory
import com.kmc.android_views_skeleton.utils.helpers.unwrapParcelable
import com.kmc.android_views_skeleton.modules.random_joke.ui.random_joke.RandomJokeViewEvent.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RandomJokeFragment : Fragment() {

    private lateinit var viewModel: RandomJokeViewModel
    private var _binding: FragmentRandomJokeBinding? = null
    val binding get() = _binding!!

    /***
     * Lifecycle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            viewModel = it.unwrapParcelable("viewModel")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRandomJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        setViewsClickActions()
        observeViewModelChanges()
        viewModel.onEvent(viewDidAppear)
    }

    /***
     * Private Methods
     */
    private fun setViewsClickActions() {

        binding.containerLy.setOnClickListener {
            viewModel.onEvent(didTapTheScreen)
        }

        binding.searchBtn.setOnClickListener {
            viewModel.onEvent(didTapSearchBtn)
        }

        binding.categoriesDropDown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCategory = JokeCategory.valueOf(parent?.getItemAtPosition(position).toString())
                viewModel.onEvent(didSelectCategory(selectedCategory))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }
    }

    private fun observeViewModelChanges() {

        viewModel.currentJoke.onEach {

            binding.jokeImv.load(data = it.imageUrl) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
                scale(Scale.FIT)
            }
            binding.jokeTv.text = it.text
        }.launchIn(lifecycleScope)
    }

    companion object {

        @JvmStatic
        fun newInstance(viewModel: RandomJokeViewModel) = RandomJokeFragment().apply {
            arguments = bundleOf("viewModel" to viewModel)
        }
    }
}