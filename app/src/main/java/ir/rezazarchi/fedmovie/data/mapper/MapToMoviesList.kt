package ir.rezazarchi.fedmovie.data.mapper

import ir.rezazarchi.fedmovie.data.remote.dto.MoviesResponse
import ir.rezazarchi.fedmovie.domain.repo.model.Movie

fun MoviesResponse.mapToMoviesList() =
    moviesData.map {
        Movie(
            it.id,
            it.title,
            it.poster,
            it.year,
            it.rated,
            it.released,
            it.runtime,
            it.director,
            it.writer,
            it.actors,
            it.plot,
            it.country,
            it.awards,
            it.metascore,
            it.imdbRating,
            it.imdbVotes,
            it.imdbId,
            it.type,
            it.genres,
            it.images,
        )
    }
