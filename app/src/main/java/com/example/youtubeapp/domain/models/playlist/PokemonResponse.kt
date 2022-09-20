package com.example.cleanarchicture.domain.models

import com.google.gson.annotations.SerializedName

data class PokemonResponse<T>(

    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    val results: List<T>,

    @SerializedName("next")
    val next: String,

    @SerializedName("previous")
    val previous: String
)
