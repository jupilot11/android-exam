package com.joeydee.android_exam.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joeydee.android_exam.R
import com.joeydee.android_exam.api.RandomUsersResponse
import com.joeydee.android_exam.databinding.ActivityMainBinding
import com.joeydee.android_exam.objects.UserResult
import com.joeydee.android_exam.utils.Resource
import com.joeydee.android_exam.utils.ToastHelper
import com.joeydee.android_exam.viewmodel.RandomUserViewModel

class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }
    lateinit var viewModel: RandomUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        viewModel.user.observe(this, Observer {
            when (it?.status) {
                Resource.Status.LOADING -> {
                    ToastHelper.showText("Loading")
                }
                Resource.Status.ERROR -> {
                    ToastHelper.showText(it.message)
                    binding.sample.text = "ERROR"
                    Log.wtf("ERRORR",it.message)
                }
                Resource.Status.SUCCESS -> {

                    if (it.data != null) {
                        val data = it.data as List<UserResult>
                        binding.sample.text = data[0].name!!.first

                    } else {
                        ToastHelper.showText("An Error has occurred")
                        binding.sample.text = "SUCCESS"

                    }
                }
            }
        })
    }
}