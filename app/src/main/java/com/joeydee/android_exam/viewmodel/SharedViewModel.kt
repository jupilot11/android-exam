package com.joeydee.android_exam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    var user_id = MutableLiveData<String>()
    fun setUserId(type: String) {
        user_id.value = type
    }

    fun getId(): LiveData<String> {
        return user_id
    }
}