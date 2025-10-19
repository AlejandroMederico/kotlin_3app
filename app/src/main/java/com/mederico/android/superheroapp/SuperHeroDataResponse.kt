package com.mederico.android.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: List<SuperHeroItemResponse>
) {

}

data class SuperHeroItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperHeroImageResponse
)
data class SuperHeroImageResponse(
    @SerializedName("url") val url: String
)

data class SuperHeroDetailResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("image") val image: SuperHeroImageResponse,
    @SerializedName("biography") val biography: BiographyResponse
)

data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class BiographyResponse(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String
)


