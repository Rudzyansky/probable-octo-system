package ru.ft.giphyapp.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ft.giphyapp.R
import ru.ft.giphyapp.Tags
import ru.ft.giphyapp.databinding.FragmentHomeBinding
import ru.ft.giphyapp.ui.util.appComponent

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels {
        requireContext().appComponent.homeViewModelFactory
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
        Log.d(Tags.AndroidLifecycle, "Fragment onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(Tags.AndroidLifecycle, "Fragment onDetach")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.a
    }
}