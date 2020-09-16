package com.demo.shaadi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.shaadi.R
import com.demo.shaadi.model.Result
import com.demo.shaadi.model.UserInfo
import kotlinx.android.synthetic.main.item_user_data.view.*

class UserAdapter :
    PagedListAdapter<UserInfo, UserAdapter.UserViewHolder>(differCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_data, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userInfo = getItem(position)
        holder.itemView.apply {
            userInfo?.let {
                Glide.with(this).load(userInfo.image).into(ivUserImage)
                tvTitle.text = userInfo.title
                tvName.text = userInfo.firstName + " " + userInfo.lastName
                tvCity.text = userInfo.city
                tvCountry.text = userInfo.country
                tvGender.text = userInfo.gender
                tvAge.text = userInfo.age
            }
        }

    }

companion object {
    private val differCallBack = object : DiffUtil.ItemCallback<UserInfo>() {

        override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return oldItem.email == newItem.email
        }

        override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return oldItem == newItem
        }
    }
}

inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}