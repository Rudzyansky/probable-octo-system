package ru.ft.giphyapp.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ft.giphyapp.App
import ru.ft.giphyapp.R
import ru.ft.giphyapp.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels { vmFactory }
    private val binding by viewBinding(FragmentHomeBinding::bind)

    @Inject
    lateinit var vmFactory: HomeViewModel.Factory

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
        Log.d("AAA", "Fragment onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("AAA", "Fragment onDetach")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.a
    }
}