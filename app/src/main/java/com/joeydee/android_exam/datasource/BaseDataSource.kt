package com.joeydee.android_exam.datasource

import com.joeydee.android_exam.utils.ResultHandler
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

open class BaseDataSource {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultHandler<T> {
        return try {
            val myResp = call.invoke()
            if (myResp.isSuccessful) {
                ResultHandler.Success(myResp.body()!!)
            } else {
                ResultHandler.Error(null)
            }
        } catch (e: Exception) {
            ResultHandler.Error(e)
        } catch (e: UnknownHostException) {
            ResultHandler.Error(e)
        } catch (e: IOException) {
            ResultHandler.Error(e)
        }
    }
}