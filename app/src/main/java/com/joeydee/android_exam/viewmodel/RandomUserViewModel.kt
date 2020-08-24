package com.joeydee.android_exam.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.joeydee.android_exam.datasource.RandomUserDataSource
import com.joeydee.android_exam.db.RandomUserDb
import com.joeydee.android_exam.model.RandomUserRepository
import com.joeydee.android_exam.objects.UserResult
import com.joeydee.android_exam.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomUserViewModel(application: Application) : AndroidViewModel(application){
    var repo :RandomUserRepository
    var data: MutableLiveData<List<UserResult?>> = MutableLiveData()

    init {
        val db = RandomUserDb
            .getInstance(application)
        repo = RandomUserRepository(db, dataSource = RandomUserDataSource())
    }

    val user = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            emit(repo.getRandomList())
        } catch (e: Exception) {
            emit(Resource.error(null, e.toString()))
        }
    }


}