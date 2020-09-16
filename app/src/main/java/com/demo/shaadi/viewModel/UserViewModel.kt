package com.demo.shaadi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.demo.shaadi.model.UserInfo
import com.demo.shaadi.repository.UserBoundaryCallback
import com.demo.shaadi.repository.UserItemDataSource.Companion.PAGE_SIZE
import com.demo.shaadi.repository.UsersRepository
import kotlinx.coroutines.Dispatchers

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
        livePageListBuilder.setBoundaryCallback(UserBoundaryCallback(Dispatchers.IO,usersRepository))
        return livePageListBuilder

    }
}