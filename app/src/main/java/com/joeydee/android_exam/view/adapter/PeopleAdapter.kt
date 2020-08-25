package com.joeydee.android_exam.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.joeydee.android_exam.R
import com.joeydee.android_exam.RandomUserApplication
import com.joeydee.android_exam.databinding.UserListBinding
import com.joeydee.android_exam.objects.UserResult
import com.joeydee.android_exam.viewmodel.RandomUserViewModel

class PeopleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var userList: List<UserResult> = ArrayList()
    lateinit var viewModel: RandomUserViewModel

    fun setData(data: List<UserResult>) {
        this.userList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: UserListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_list, parent, false
        )
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val items = userList[position]
        (holder as UserViewHolder).binding.run {
            tvName.text = "${items.name!!.first} ${items.name!!.last}"
            tvPhonenumber.text = items.cell
            imgBackdrop.setImageCenter(
                url = items.picture!!.large,
                context = RandomUserApplication.context
            )
        }
    }
    class UserViewHolder(var binding: UserListBinding) : RecyclerView.ViewHolder(binding.root)
}

fun ImageView.setImageCenter(url: String?, context: Context) {

    Glide.with(context)
        .load(url)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
        .placeholder(R.drawable.ic_baseline_broken_image_24)
        .error(R.drawable.ic_baseline_broken_image_24)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}