package com.example.docusignlivepad.data

import com.example.docusignlivepad.model.TokenResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface DocuSignService {

    @Headers("Content-Type: application/json")
    @POST("oauth/token")
    suspend fun fetchToken(
        @Header("Authorization") authorization: String,
        @Body requestBody: Map<String, String>
    ) : Response<TokenResponseModel>


}