package ru.ft.giphyapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ft.giphyapp.R
import ru.ft.giphyapp.databinding.FragmentHomeBinding
import ru.ft.giphyapp.ui.util.BaseFragment
import ru.ft.giphyapp.ui.util.appComponent

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels {
        requireContext().appComponent.homeViewModelFactory
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.a
    }
}