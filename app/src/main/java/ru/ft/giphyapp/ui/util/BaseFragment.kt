package ru.ft.giphyapp.ui.util

import android.content.Context
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import ru.ft.giphyapp.util.Tags


abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(Tags.AndroidLifecycle, "Fragment ${javaClass.canonicalName} onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(Tags.AndroidLifecycle, "Fragment ${javaClass.canonicalName} onDetach")
    }
}