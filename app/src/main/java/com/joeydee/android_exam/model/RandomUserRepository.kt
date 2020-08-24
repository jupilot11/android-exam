package com.joeydee.android_exam.model

import com.joeydee.android_exam.datasource.RandomUserDataSource
import com.joeydee.android_exam.db.RandomUserDb
import com.joeydee.android_exam.objects.UserResult
import com.joeydee.android_exam.utils.Resource
import com.joeydee.android_exam.utils.ResultHandler

class RandomUserRepository(var db: RandomUserDb, var dataSource: RandomUserDataSource) {

    suspend fun getRandomList(): Resource<List<UserResult>> {
        lateinit var response: Resource<List<UserResult>>

        try {
            if (db.randomUserDao.getAll().isNullOrEmpty()) {
                when (val data = dataSource.getUserList()) {
                    is ResultHandler.Success -> {
                        if (!data.data!!.results.isNullOrEmpty()) {
                            response = Resource.success(data.data!!.results, "Success")!!
                            db.randomUserDao.insertAll(data.data.results!!)
                        } else {
                            response = Resource.error(
                                null,
                                "System Encountered an error. Please try again later or contact customer service"
                            )!!
                        }
                    }
                    is ResultHandler.Error -> {
                        response = Resource.error(
                            null,
                            "System Encountered an error. Please try again later or contact customer service"
                        )!!
                    }
                }
            } else {
                response = Resource.success(db.randomUserDao.getAll(), "Success")!!
            }
        } catch (e: Exception) {
            response = Resource.error(null, e.toString())!!
        }
        return response
    }

    fun getAll(): List<UserResult> {
        return db.randomUserDao.getAll()
    }

}