package com.demo.shaadi.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.demo.shaadi.api.RetrofitInstance
import com.demo.shaadi.api.UsersAPICall
import com.demo.shaadi.dao.AppDatabase
import com.demo.shaadi.model.Result
import com.demo.shaadi.model.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserItemDataSource(coroutineContext: CoroutineContext) :
    PageKeyedDataSource<Int, Result>() {

    private val apiService = RetrofitInstance.getClient().create(UsersAPICall::class.java)

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    companion object {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 20
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        scope.launch {
            try {
                val response = apiService.getUsersList(PAGE_SIZE, FIRST_PAGE)
                when {
                    response.isSuccessful -> {
                        val listing = response.body()?.userData
                        callback.onResult(listing ?: listOf(), null, FIRST_PAGE + 1)
                    }
                }

            } catch (exception: Exception) {
                Log.e("UserItemDataSource", "Failed to fetch data!")
            }
        }

    }
// Previous page
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        scope.launch {
            try {
                val response =
                    apiService.getUsersList(PAGE_SIZE,page = params.key)
                when {
                    response.isSuccessful -> {

                        val mKey = if (params.key > 1) {
                            params.key - 1 // Decrement page number
                        } else {
                            null
                        }
                        val listing = response.body()?.userData

                        callback.onResult(listing ?: listOf(), mKey)
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }
    // next page
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
       scope.launch {
           try {
               val response =
                   apiService.getUsersList(PAGE_SIZE, page = params.key)
               when {
                   response.isSuccessful -> {

                       val mKey = if (params.key > 1) {
                           params.key + 1  // Increment page number
                       } else {
                           null
                       }
                       val listing = response.body()?.userData

                       callback.onResult(listing ?: listOf(), mKey)
                   }
               }

           } catch (exception: Exception) {
               Log.e("PostsDataSource", "Failed to fetch data!")
           }
       }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}