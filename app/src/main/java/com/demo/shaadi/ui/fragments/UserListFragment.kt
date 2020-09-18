package com.demo.shaadi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.shaadi.R
import com.demo.shaadi.adapter.UserAdapter
import com.demo.shaadi.ui.HomeActivity
import com.demo.shaadi.utils.Constants.Companion.IS_DATA_SET
import com.demo.shaadi.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_users_list.*

class UserListFragment : MyBaseFragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    /**
     * This will be trigger when network state change.
     */
    override fun onNetworkChange(isConnected: Boolean) {
        val isDataExist = mPreferenceUtils.getValue(IS_DATA_SET, false)
        if (isConnected || isDataExist) {
            showProgressBar()
            observeLiveData()
            tv_no_data.visibility = View.GONE
            rvUserList.visibility = View.VISIBLE
        } else {
            tv_no_data.visibility = View.VISIBLE
            rvUserList.visibility = View.GONE
            hideProgressBar()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as HomeActivity).userViewModel
        setUpRecyclerView()
        userAdapter.setOnItemClickListener {
            viewModel.updateUserState(it.email, it.userState)
            userAdapter.notifyDataSetChanged()
        }
    }

    /**
     * This fun will observe users live data which are returned from viewModel
     */
    private fun observeLiveData() {
        viewModel.getPosts().observe(viewLifecycleOwner, Observer { userList ->
            if (userList.size > 0) {
                hideProgressBar()
                userAdapter.submitList(userList)
                mPreferenceUtils.setValue(IS_DATA_SET, true)
            } else {
                showProgressBar()
            }
        })
    }

    /**
     * Set up RecyclerView for viewing the list
     */
    private fun setUpRecyclerView() {
        userAdapter = UserAdapter()
        rvUserList.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

}