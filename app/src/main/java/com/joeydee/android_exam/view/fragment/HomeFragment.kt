package com.joeydee.android_exam.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.joeydee.android_exam.R
import com.joeydee.android_exam.databinding.FragmentHomeBinding
import com.joeydee.android_exam.objects.UserResult
import com.joeydee.android_exam.utils.Resource
import com.joeydee.android_exam.utils.ToastHelper
import com.joeydee.android_exam.utils.onClick
import com.joeydee.android_exam.view.adapter.PeopleAdapter
import com.joeydee.android_exam.viewmodel.RandomUserViewModel
import com.joeydee.android_exam.viewmodel.SharedViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: RandomUserViewModel
    lateinit var navController: NavController
    private var peopleAdapter = PeopleAdapter()
    val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        initList()
        getPersons()
    }


    private fun getPersons() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it?.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.ERROR -> {
                    ToastHelper.showText(it.message)
                    Log.wtf("ERRORR", it.message)
                }
                Resource.Status.SUCCESS -> {

                    if (it.data != null) {
                        val data = it.data as List<UserResult>
                        peopleAdapter.setData(data)
                    } else {
                        ToastHelper.showText("An Error has occurred")
                    }
                }
            }
        })
    }

    private fun initList() {
        binding.rvPersonList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPersonList.adapter = peopleAdapter
        binding.rvPersonList.onClick { _, i, _ ->
            peopleAdapter.userList[i].let {
                navController.navigate(R.id.action_menuFragment_to_profileFragment)
                sharedViewModel.setUserId(it.primaryKey!!)
            }
        }
    }
}