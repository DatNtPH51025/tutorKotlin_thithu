package com.poly.tutorkotlin.network

import com.poly.tutorkotlin.models.Cat
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("cats")
    suspend fun getCats(
        @Query("tags") tags: String = "cute",
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 10
    ): List<Cat>
}
