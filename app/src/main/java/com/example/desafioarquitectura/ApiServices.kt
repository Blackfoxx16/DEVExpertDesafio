package com.example.desafioarquitectura

import com.example.desafioarquitectura.ui.theme.ResponseCharacters
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {


    @GET("character")
    suspend fun getCharactersAll(@Query("page") page: String): ResponseCharacters


}