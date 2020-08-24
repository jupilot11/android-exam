package com.joeydee.android_exam.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/?&results=20")
    suspend fun getList(): Response<RandomUsersResponse>

}