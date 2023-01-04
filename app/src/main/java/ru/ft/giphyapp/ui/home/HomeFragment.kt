package ru.ft.giphyapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ft.giphyapp.R
import ru.ft.giphyapp.databinding.FragmentHomeBinding
import ru.ft.giphyapp.ui.util.BaseFragment
import ru.ft.giphyapp.ui.util.GiphyAdapter
import ru.ft.giphyapp.ui.util.appComponent
import ru.ft.giphyapp.util.CoroutineDispatcherProvider
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
        binding.feedRv.apply {

            // TODO: OnEndScroll { viewModel.nextPage() }

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = GiphyAdapter(dispatchers).apply {
                setLoadContent { gifObject, callback ->
                    callback(viewModel.load(gifObject))
                }
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    viewModel.currentList.collect {
                        addToList(it.gifs, it.currentPage * it.gifs.size)
                    }
                }
            }
        }
    }
}