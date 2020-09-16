package com.demo.shaadi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.shaadi.R
import com.demo.shaadi.adapter.UserAdapter
import com.demo.shaadi.ui.HomeActivity
import com.demo.shaadi.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_users_list.*

class UserListFragment : Fragment(R.layout.fragment_users_list) {

    lateinit var viewModel: UserViewModel
    lateinit var userAdapter: UserAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as HomeActivity).userViewModel

        observeLiveData()
        setUpRecyclerView()

    }

    private fun observeLiveData() {
        viewModel.getPosts().observe(viewLifecycleOwner, Observer {
            userAdapter.submitList(it)
        })
    }

    private fun setUpRecyclerView() {
        userAdapter = UserAdapter()
        rvUserList.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }
}