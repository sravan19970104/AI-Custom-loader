package com.loadingbar.generator.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoadingBarApiService {
    @POST("generate-loader")
    suspend fun generateLoadingBar(@Body request: GenerateLoaderRequest): Response<GenerateLoaderResponse>
    
    @GET("health")
    suspend fun healthCheck(): Response<HealthResponse>
}

data class GenerateLoaderRequest(val prompt: String, val speed: Int, val color: String)
data class GenerateLoaderResponse(val animationJson: String, val success: Boolean, val message: String)
data class HealthResponse(val status: String, val timestamp: Long)