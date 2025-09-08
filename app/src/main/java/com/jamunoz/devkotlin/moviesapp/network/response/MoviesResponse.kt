package com.jamunoz.devkotlin.moviesapp.network.response

import com.google.gson.annotations.SerializedName
import com.jamunoz.devkotlin.moviesapp.models.MovieModel

data class MoviesResponse(
    @SerializedName("results")
    var results: List<MovieModel>


)
