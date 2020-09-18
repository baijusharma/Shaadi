package com.demo.shaadi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.demo.shaadi.R
import com.demo.shaadi.dao.AppDatabase
import com.demo.shaadi.repository.UsersRepository
import com.demo.shaadi.viewModel.UserViewModel
import com.demo.shaadi.viewModel.UserViewModelProviderFactory

class HomeActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val repository = UsersRepository(AppDatabase(this))
        val viewModelProviderFactory = UserViewModelProviderFactory(application,repository)
        userViewModel = ViewModelProvider(this, viewModelProviderFactory).get(UserViewModel::class.java)
    }
}
