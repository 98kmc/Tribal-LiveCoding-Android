package com.kmc.android_views_skeleton.utils.networking

import com.kmc.android_views_skeleton.utils.Resource
import retrofit2.HttpException
import retrofit2.Response

interface SafeApiCaller {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = apiCall.invoke()
            val body = response.body()

            if (!response.isSuccessful) return Resource.Failure(response.message())
            if (body == null) return Resource.Failure("Null data found")

            return Resource.Success(body)
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> Resource.Failure("UnauthorizedUnauthorized")
                404 -> Resource.Failure("Not Found")
                500 -> Resource.Failure("Internal Server Error")
                else -> Resource.Failure("Unknown Error")
            }
        } catch (e: Throwable) {
            Resource.Failure(e.message.toString())
        } catch (e: Exception) {
            Resource.Failure(e.message.toString())
        }
    }
}