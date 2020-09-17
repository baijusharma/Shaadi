package com.demo.shaadi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.demo.shaadi.model.UserInfo
import com.demo.shaadi.repository.UserBoundaryCallback
import com.demo.shaadi.repository.UsersRepository
import com.demo.shaadi.utils.Constants.Companion.PAGE_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(val usersRepository: UsersRepository) : ViewModel() {

    private var usersLiveData: LiveData<PagedList<UserInfo>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        usersLiveData = initializedPagedListBuilder(config).build()
    }

    fun getPosts(): LiveData<PagedList<UserInfo>> = usersLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, UserInfo> {

        val livePageListBuilder = LivePagedListBuilder<Int, UserInfo>(
            usersRepository.posts(),
            config
        )
        livePageListBuilder.setBoundaryCallback(
            UserBoundaryCallback(
                Dispatchers.IO,
                usersRepository
            )
        )
        return livePageListBuilder

    }

    fun updateUserState(email: String, userState: Int) = viewModelScope.launch {
        usersRepository.updateUserState(email,userState)
    }
}