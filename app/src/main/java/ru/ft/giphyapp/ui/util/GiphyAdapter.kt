package ru.ft.giphyapp.ui.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.droidsonroids.gif.GifDrawable
import ru.ft.giphyapp.databinding.FeedGifItemBinding
import ru.ft.giphyapp.databinding.FeedLoadingItemBinding
import ru.ft.giphyapp.domain.entity.GifObject
import ru.ft.giphyapp.util.CoroutineDispatcherProvider
import ru.ft.giphyapp.util.Tags

class GiphyAdapter(
    private val dispatchers: CoroutineDispatcherProvider
) : RecyclerView.Adapter<CommonViewHolder>() {

    private val scope = CoroutineScope(dispatchers.Default)

    private val items = mutableMapOf<Int, GifObject>()
    val jobs = mutableMapOf<Int, Job>()

    private var loadContent: suspend (GifObject, suspend (ByteArray) -> Unit) -> Unit = { _, _ -> }

    fun addToList(list: List<GifObject>, offset: Int) {
        val data = list.mapIndexed { index, gifObject -> offset + index to gifObject }
        items.putAll(data)
//        items.clear()
//        items.putAll(items.filter { it.key > offset - list.size } + data)
        Log.d(Tags.GiphyAdapter, "Offset: $offset; List: $list")
        notifyItemRangeChanged(offset, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            LOADING_TYPE -> FeedLoadingItemBinding.inflate(inflater, parent, false)
            GIF_TYPE -> FeedGifItemBinding.inflate(inflater, parent, false)
            else -> error("Unknown viewType $viewType")
        }
        return CommonViewHolder(binding)
    }

    override fun getItemCount() = (items.keys.maxOrNull() ?: -1) + 1

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        when (holder.binding) {
            is FeedLoadingItemBinding -> {}
            is FeedGifItemBinding -> with(holder.binding) {
                val item = items[position] ?: error("Item not found by position $position")

                jobs[position]?.apply {
                    cancel()
                    jobs.remove(position)
                }

                // TODO: store jobs for cancel in recycle case
                val job = scope.launch {
                    withContext(dispatchers.Main) {
                        contentIv.visibility = View.INVISIBLE
                    }
                    loadContent(item) {
                        val drawable = GifDrawable(it).apply { loopCount = 0 }
                        withContext(dispatchers.Main) {
                            contentIv.setImageDrawable(drawable)
                            contentIv.visibility = View.VISIBLE
                        }
                    }
                }
                jobs[position] = job
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.keys.contains(position)) GIF_TYPE else LOADING_TYPE
    }

    fun setLoadContent(l: suspend (GifObject, callback: suspend (ByteArray) -> Unit) -> Unit) {
        loadContent = l
    }

    companion object {
        private const val LOADING_TYPE = 1
        private const val GIF_TYPE = 2
    }
}

class CommonViewHolder(val binding: ViewBinding) : ViewHolder(binding.root)
