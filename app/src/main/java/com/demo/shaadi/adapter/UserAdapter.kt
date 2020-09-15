package com.demo.shaadi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.shaadi.R
import com.demo.shaadi.model.UserInfo
import kotlinx.android.synthetic.main.item_user_data.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_data, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userInfo = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(userInfo.image).into(ivUserImage)
            tvTitle.text = userInfo.title
            tvName.text = userInfo.firstName + "" + userInfo.lastName
            tvCity.text = userInfo.city
            tvCountry.text = userInfo.country
            tvGender.text = userInfo.gender
            tvAge.text = userInfo.age.toString()
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<UserInfo>() {

        override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}