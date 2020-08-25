package com.joeydee.android_exam.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.joeydee.android_exam.R
import com.joeydee.android_exam.api.RandomUsersResponse
import com.joeydee.android_exam.databinding.ActivityMainBinding
import com.joeydee.android_exam.objects.UserResult
import com.joeydee.android_exam.utils.Resource
import com.joeydee.android_exam.utils.ToastHelper
import com.joeydee.android_exam.viewmodel.RandomUserViewModel

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = findNavController(R.id.nav_fragment)
    }
}