package com.pwr.wanderway.network

import retrofit2.Response

object ApiErrorHandler {
    fun handleResponseError(response: Response<*>): String {
        return response.errorBody()?.string()?.takeIf { it.isNotBlank() }
            ?: "An unknown error occurred"
    }
}