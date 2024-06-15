package com.example.docusignlivepad.data

import com.example.docusignlivepad.model.TokenResponseModel
import retrofit2.Response

class DocuSignRepository(private val docuSignService: DocuSignService) {

    suspend fun fetchToken(authorization: String, requestBody: Map<String, String>) : Response<TokenResponseModel> {
        return docuSignService.fetchToken(authorization, requestBody)
    }
}