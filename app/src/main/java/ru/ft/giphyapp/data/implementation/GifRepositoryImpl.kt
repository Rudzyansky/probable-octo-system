package ru.ft.giphyapp.data.implementation

import kotlinx.coroutines.withContext
import ru.ft.giphyapp.data.dto.GifObjectDto
import ru.ft.giphyapp.data.dto.ListGifsDto
import ru.ft.giphyapp.data.service.GiphyApi
import ru.ft.giphyapp.domain.entity.GifListObject
import ru.ft.giphyapp.domain.entity.GifObject
import ru.ft.giphyapp.domain.entity.GiphyRating
import ru.ft.giphyapp.domain.repository.GifRepository
import ru.ft.giphyapp.util.CoroutineDispatcherProvider
import javax.inject.Inject
import kotlin.math.ceil

class GifRepositoryImpl @Inject constructor(
    private val api: GiphyApi,
    private val dispatchers: CoroutineDispatcherProvider
) : GifRepository {

    override suspend fun getTrending(page: Int, rating: GiphyRating): GifListObject =
        withContext(dispatchers.IO) {
            api.trending(
                offset = page * LIMIT_ON_PAGE,
                limit = LIMIT_ON_PAGE,
                rating = rating.forApi
            ).domain
        }

    private val GiphyRating.forApi
        get() = when (this) {
            GiphyRating.G -> "g"
            GiphyRating.PG -> "pg"
            GiphyRating.PG13 -> "pg-13"
            GiphyRating.R -> "r"
        }

    private val GifObjectDto.domain get() = GifObject(id = id, url = url)

    private val ListGifsDto.domain
        get() = GifListObject(
            gifs = data.map { it.domain },
            currentPage = pagination.offset / LIMIT_ON_PAGE,
            pagesCount = ceil(pagination.totalCount / LIMIT_ON_PAGE.toDouble()).toInt(),
        )

    companion object {
        private const val LIMIT_ON_PAGE = 25
    }
}