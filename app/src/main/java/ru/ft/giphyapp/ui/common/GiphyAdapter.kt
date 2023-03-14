package ru.ft.giphyapp.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.droidsonroids.gif.GifDrawable
import ru.ft.giphyapp.databinding.FeedErrorItemBinding
import ru.ft.giphyapp.databinding.FeedGifItemBinding
import ru.ft.giphyapp.databinding.FeedLoadingItemBinding
import ru.ft.giphyapp.domain.entity.GifObject
import ru.ft.giphyapp.domain.entity.LoadedContent
import ru.ft.giphyapp.util.CoroutineDispatcherProvider

class GiphyAdapter(
    private val dispatchers: CoroutineDispatcherProvider
) : ListAdapter<DisplayContent, CommonViewHolder>(DisplayContentDiffUtil()) {

    private val scope = CoroutineScope(dispatchers.Default)

    val jobs = mutableMapOf<Int, Job>()

    private var loadContent: suspend (GifObject, suspend (LoadedContent) -> Unit) -> Unit =
        { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            LOADING_TYPE -> FeedLoadingItemBinding.inflate(inflater, parent, false)
            GIF_TYPE -> FeedGifItemBinding.inflate(inflater, parent, false)
            ERROR_TYPE -> FeedErrorItemBinding.inflate(inflater, parent, false)
            else -> error("Unknown viewType $viewType")
        }
        return CommonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        when (holder.binding) {
            is FeedLoadingItemBinding -> with(holder.binding) {
                val item = getItem(position) as DisplayContent.Loading
                onBind(position, item)
            }
            is FeedGifItemBinding -> with(holder.binding) {
                val item = getItem(position) as DisplayContent.Gif
                onBind(position, item)
            }
            is FeedErrorItemBinding -> with(holder.binding) {
                val item = getItem(position) as DisplayContent.Error
                onBind(position, item)
            }
        }
    }

    private fun FeedLoadingItemBinding.onBind(position: Int, item: DisplayContent.Loading) {
    }

    private fun FeedGifItemBinding.onBind(position: Int, item: DisplayContent.Gif) {

                val drawable = GifDrawable(item.gif).apply { loopCount = 0 }
                contentIv.setImageDrawable(drawable)
                contentIv.visibility = View.VISIBLE

//        jobs[position]?.apply {
//            cancel()
//            jobs.remove(position)
//        }
//
//        // TODO: store jobs for cancel in recycle case
//        val job = scope.launch {
//            withContext(dispatchers.Main) {
//                contentIv.visibility = View.INVISIBLE
//            }
////                    loadContent(item) {
////                        val drawable = GifDrawable(it.gif).apply { loopCount = 0 }
////                        withContext(dispatchers.Main) {
////                            contentIv.setImageDrawable(drawable)
////                            contentIv.visibility = View.VISIBLE
////                        }
////                    }
//        }
//        jobs[position] = job
    }

    private fun FeedErrorItemBinding.onBind(position: Int, item: DisplayContent.Error) {
        errorMessageTv.text = item.message
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DisplayContent.Loading -> LOADING_TYPE
            is DisplayContent.Gif -> GIF_TYPE
            is DisplayContent.Error -> ERROR_TYPE
        }
    }

    fun setLoadContent(l: suspend (GifObject, callback: suspend (LoadedContent) -> Unit) -> Unit) {
        loadContent = l
    }

    companion object {
        private const val LOADING_TYPE = 1
        private const val GIF_TYPE = 2
        private const val ERROR_TYPE = 3
    }
}

class CommonViewHolder(val binding: ViewBinding) : ViewHolder(binding.root)

class DisplayContentDiffUtil : DiffUtil.ItemCallback<DisplayContent>() {

    override fun areItemsTheSame(oldItem: DisplayContent, newItem: DisplayContent): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: DisplayContent, newItem: DisplayContent): Boolean {
        return oldItem == newItem
    }
}
