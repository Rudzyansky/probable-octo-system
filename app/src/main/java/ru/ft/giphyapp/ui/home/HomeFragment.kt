package ru.ft.giphyapp.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ft.giphyapp.R
import ru.ft.giphyapp.databinding.FragmentHomeBinding
import ru.ft.giphyapp.ui.util.BaseFragment
import ru.ft.giphyapp.ui.util.GiphyAdapter
import ru.ft.giphyapp.ui.util.appComponent
import ru.ft.giphyapp.util.CoroutineDispatcherProvider
import ru.ft.giphyapp.util.Tags
import javax.inject.Inject

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels {
        requireContext().appComponent.homeViewModelFactory
    }

    @Inject
    lateinit var dispatchers: CoroutineDispatcherProvider

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val giphyAdapter = GiphyAdapter(dispatchers).apply {
            setLoadContent { gifObject, callback ->
                callback(viewModel.load(gifObject))
            }
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.currentList.collect {
                    addToList(it.gifs, it.currentPage * it.gifs.size)
                }
            }
        }
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val onEndScroll = object : OnScrollListener() {
            private var isLoading = false

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val total = linearLayoutManager.itemCount
                val first = linearLayoutManager.findFirstVisibleItemPosition()
                val last = linearLayoutManager.findLastVisibleItemPosition()

                // Load down
                if (dy > 0) {
                    giphyAdapter.jobs.run {
                        keys.filter { it < first }
                            .onEach { get(it)?.cancel() }
                            .forEach { remove(it) }
                    }

                    if (!isLoading && last == total - 1) {
                        isLoading = true
                        Log.d(Tags.FeedLifecycle, "Load down (first = $first, last = $last, total = $total)")
                        viewModel.nextPage()
                        isLoading = false
                    }
                }

                // Load up
                if (dy < 0) {
                    giphyAdapter.jobs.run {
                        keys.filter { it > last }
                            .onEach { get(it)?.cancel() }
                            .forEach { remove(it) }
                    }

//                    if (!isLoading && last == total - 1) {
//                        isLoading = true
//                        Log.d(Tags.FeedLifecycle, "Load up (first = $first, last = $last, total = $total)")
//                        viewModel.previousPage()
//                        isLoading = false
//                    }
                }
            }
        }

        binding.feedRv.apply {
            layoutManager = linearLayoutManager
            adapter = giphyAdapter
            addOnScrollListener(onEndScroll)
        }
    }
}