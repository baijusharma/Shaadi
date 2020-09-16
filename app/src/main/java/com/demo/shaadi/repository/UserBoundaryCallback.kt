package com.demo.shaadi.repository

import android.util.Log
import androidx.paging.PagedList
import com.demo.shaadi.api.RetrofitInstance
import com.demo.shaadi.api.UsersAPICall
import com.demo.shaadi.dao.AppDatabase
import com.demo.shaadi.model.Result
import com.demo.shaadi.model.UserInfo
import com.demo.shaadi.repository.UserItemDataSource.Companion.FIRST_PAGE
import com.demo.shaadi.repository.UserItemDataSource.Companion.PAGE_SIZE
import com.demo.shaadi.utils.PagingRequestHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class UserBoundaryCallback(coroutineContext: CoroutineContext, usersRepository: UsersRepository) :
    PagedList.BoundaryCallback<UserInfo>() {

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)
    private val apiService = RetrofitInstance.getClient().create(UsersAPICall::class.java)
    private val mUsersRepository = usersRepository


    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        //1
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { helperCallback ->
            scope.launch {
                try {
                    val response = apiService.getUsersList(PAGE_SIZE, FIRST_PAGE)
                    when {
                        response.isSuccessful -> {
                            val listing = response.body()?.userData
                            val userList = ArrayList<UserInfo>()
                            if(listing!= null){

                                for (user in listing){
                                    val userInfo = UserInfo()
                                    userInfo.image = user.picture!!.large
                                    userInfo.title = user.name!!.title
                                    userInfo.firstName = user.name!!.first
                                    userInfo.lastName = user.name!!.last
                                    userInfo.age = user.dob!!.age
                                    userInfo.email = user.email
                                    userInfo.gender = user.gender
                                    userInfo.city = user.location!!.city
                                    userInfo.country = user.location!!.country
                                    userList.add(userInfo)
                                }
                                mUsersRepository.insert(userList)
                                helperCallback?.recordSuccess()

                            }
                        }
                    }

                } catch (exception: Exception) {
                    Log.e("UserItemDataSource", "Failed to fetch data!")
                }
            }

        }

    }

    override fun onItemAtEndLoaded(itemAtEnd: UserInfo) {
        super.onItemAtEndLoaded(itemAtEnd)
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { helperCallback ->
            scope.launch {
                try {
                    val response = apiService.getUsersList(PAGE_SIZE, FIRST_PAGE+1)
                    when {
                        response.isSuccessful -> {
                            val listing = response.body()?.userData
                            val userList = ArrayList<UserInfo>()
                            if(listing!= null){

                                for (user in listing){
                                    val userInfo = UserInfo()
                                    userInfo.image = user.picture!!.large
                                    userInfo.title = user.name!!.title
                                    userInfo.firstName = user.name!!.first
                                    userInfo.lastName = user.name!!.last
                                    userInfo.age = user.dob!!.age
                                    userInfo.email = user.email
                                    userInfo.gender = user.gender
                                    userInfo.city = user.location!!.city
                                    userInfo.country = user.location!!.country
                                    userList.add(userInfo)
                                }
                                mUsersRepository.insert(userList)
                                helperCallback?.recordSuccess()
                            }
                        }
                    }

                } catch (exception: Exception) {
                    Log.e("UserItemDataSource", "Failed to fetch data!")
                }
            }
        }
    }
}