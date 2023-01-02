package ru.ft.giphyapp.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ft.giphyapp.App
import ru.ft.giphyapp.R
import ru.ft.giphyapp.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var viewModel: HomeViewModel

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }
}