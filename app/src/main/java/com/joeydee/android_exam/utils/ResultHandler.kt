package com.joeydee.android_exam.utils

sealed class ResultHandler<out T : Any> {

    data class Success<out T : Any>(val data: T?) : ResultHandler<T>()

    data class Error(val exception: Exception?) : ResultHandler<Nothing>()

}