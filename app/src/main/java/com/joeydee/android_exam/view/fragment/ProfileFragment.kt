package com.joeydee.android_exam.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.joeydee.android_exam.R
import com.joeydee.android_exam.RandomUserApplication
import com.joeydee.android_exam.databinding.FragmentProfileBinding
import com.joeydee.android_exam.objects.Age
import com.joeydee.android_exam.viewmodel.RandomUserViewModel
import com.joeydee.android_exam.viewmodel.SharedViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var navController: NavController
    val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var viewModel: RandomUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        sharedViewModel.getId().observe(viewLifecycleOwner, Observer {
            viewModel.getPerson(it)
        })
        viewModel.person.observe(viewLifecycleOwner, Observer { items ->

            binding.imageView.setImageCenter(items.picture!!.large, RandomUserApplication.context)
            binding.tvName.text = "${items.name!!.first} ${items.name!!.last}"
            binding.tvEmail.text = items.email
            binding.tvBday.setDate(items.dob!!.date)
            binding.tvAge.text =
                "${calculateAge(converttoDate(formatDate(items.dob!!.date)!!))!!.years} y/o"
            binding.tvMobilenumber.text = items.cell
            binding.tvAddress.text = "${items.location!!.street.number!!} " +
                    " ${items.location!!.street.name!!}" +
                    " ${items.location!!.city}" +
                    " ${items.location!!.state}" +
                    " ${items.location!!.country}"
        })
    }
}

fun TextView.setDate(date: String) {
    try {
        this.text = formatDate(date)
    } catch (e: Exception) {
        this.text = "N/A"
    }
}

fun converttoDate(str_date: String): Date {
    return SimpleDateFormat("MMM dd, yyyy").parse(str_date)
}

fun formatDate(str_date: String?): String? {
    val date2: String
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    var dt: Date? = null
    try {
        dt = format.parse(str_date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    val your_format = SimpleDateFormat("MMM dd, yyyy")
    date2 = your_format.format(dt)
    return date2

}

fun calculateAge(birthDate: Date): Age? {
    var years = 0
    var months = 0
    var days = 0
    val birthDay = Calendar.getInstance()
    birthDay.timeInMillis = birthDate.time
    val currentTime = System.currentTimeMillis()
    val now = Calendar.getInstance()
    now.timeInMillis = currentTime
    years = now[Calendar.YEAR] - birthDay[Calendar.YEAR]
    val currMonth = now[Calendar.MONTH] + 1
    val birthMonth = birthDay[Calendar.MONTH] + 1
    months = currMonth - birthMonth
    if (months < 0) {
        years--
        months = 12 - birthMonth + currMonth
        if (now[Calendar.DATE] < birthDay[Calendar.DATE]) months--
    } else if (months == 0 && now[Calendar.DATE] < birthDay[Calendar.DATE]) {
        years--
        months = 11
    }
    if (now[Calendar.DATE] > birthDay[Calendar.DATE]) days =
        now[Calendar.DATE] - birthDay[Calendar.DATE] else if (now[Calendar.DATE] < birthDay[Calendar.DATE]
    ) {
        val today = now[Calendar.DAY_OF_MONTH]
        now.add(Calendar.MONTH, -1)
        days =
            now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay[Calendar.DAY_OF_MONTH] + today
    } else {
        days = 0
        if (months == 12) {
            years++
            months = 0
        }
    }
    return Age(days, months, years)
}


fun ImageView.setImageCenter(url: String?, context: Context) {

    Glide.with(context)
        .load(url)
        .circleCrop()
        .placeholder(R.drawable.ic_baseline_broken_image_24)
        .error(R.drawable.ic_baseline_broken_image_24)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}