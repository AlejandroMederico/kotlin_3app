package com.mederico.android.superheroapp

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response



interface ApiService {

    @GET("api/af5dd1fedcdcfa257d93377f44c78ce6/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

    @GET("api/af5dd1fedcdcfa257d93377f44c78ce6/{id}")
    suspend fun getSuperHeroDetails(@Path("id") superHeroId: String): Response<SuperHeroDetailResponse>
}