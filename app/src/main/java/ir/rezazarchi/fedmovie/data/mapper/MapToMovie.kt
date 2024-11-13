package ir.rezazarchi.fedmovie.data.mapper

import ir.rezazarchi.fedmovie.data.remote.dto.MovieDto
import ir.rezazarchi.fedmovie.domain.repo.model.Movie

fun MovieDto.mapToMovie(): Movie {
    return Movie(
        id,
        title,
        poster,
        year,
        rated,
        released,
        runtime,
        director,
        writer,
        actors,
        plot,
        country,
        awards,
        metascore,
        imdbRating,
        imdbVotes,
        imdbId,
        type,
        genres,
        images,
    )
}
