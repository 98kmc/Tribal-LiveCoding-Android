package com.kmc.android_views_skeleton.modules.random_joke.ui.jokes_browser

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.kmc.android_views_skeleton.databinding.FragmentJokesBrowserBinding
import com.kmc.android_views_skeleton.modules.random_joke.ui.jokes_browser.jokes_adapter.JokesRecyclerAdapter
import com.kmc.android_views_skeleton.modules.random_joke.ui.jokes_browser.JokeBrowserViewEvent.searchTextDidChange
import com.kmc.android_views_skeleton.utils.helpers.unwrapParcelable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class JokeBrowserFragment : Fragment() {

    private lateinit var viewModel: JokesBrowserViewModel
    private lateinit var jokesRecycler: JokesRecyclerAdapter
    private var _binding: FragmentJokesBrowserBinding? = null
    val binding get() = _binding!!

    /***
     * Lifecycle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { viewModel = it.unwrapParcelable("viewModel") }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentJokesBrowserBinding.inflate(inflater, container, false)

        configureJokesRecyclerView()
        addSearchBarEventListeners()

        return binding.root
    }

    /***
     * Private Methods
     */
    private fun configureJokesRecyclerView() {

        jokesRecycler = JokesRecyclerAdapter()

        binding.jokesRv.run {
            adapter = jokesRecycler
        }

        // observe the viewModel's list
        viewModel.jokesList.onEach { fetchedJokeList ->

            jokesRecycler.applySnapshot(newList = fetchedJokeList)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
    private fun addSearchBarEventListeners() {

        binding.searchBar.setOnEditorActionListener { v, actionId, _ ->

            if (actionId != EditorInfo.IME_ACTION_SEARCH || v.text == null || v.text.length < 3) {

                return@setOnEditorActionListener true
            }

            viewModel.onEvent(searchTextDidChange(v.text.toString()))
            return@setOnEditorActionListener false
        }

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s == null) return
                if (s.length < 3) return

                viewModel.onEvent(searchTextDidChange(s.toString()))
            }

            override fun afterTextChanged(s: Editable?) { }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(viewModel: JokesBrowserViewModel) = JokeBrowserFragment().apply {
            arguments = bundleOf("viewModel" to viewModel)
        }
    }
}