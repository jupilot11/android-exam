package com.joeydee.android_exam.datasource

import com.joeydee.android_exam.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class RandomUserDataSource : BaseDataSource() {
    private val apiClient = ApiClient()
    private val apiInerface = apiClient.getUserService()

    suspend fun getUserList() = withContext(Dispatchers.IO) {
        safeApiCall(
            call = {
                apiInerface!!.getList()
            }
        )
    }
}
