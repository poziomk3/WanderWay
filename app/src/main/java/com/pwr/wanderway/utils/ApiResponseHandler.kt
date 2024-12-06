package com.pwr.wanderway.utils

import retrofit2.Response

object ApiResponseHandler {
    fun <T> handleResponse(response: Response<T>): Result<T?> {
        return if (response.isSuccessful) {
            if (response.code() == 204) {
                Result.success(null)
            } else {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Unexpected empty response body"))
            }
        } else {
            Result.failure(Exception(response.errorBody()?.string()?.takeIf { it.isNotBlank() }
                ?: "An unknown error occurred"))
        }
    }
}
