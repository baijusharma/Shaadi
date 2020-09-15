package com.demo.shaadi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.shaadi.repository.UsersRepository

class UserViewModelProviderFactory( val usersRepository: UsersRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(usersRepository) as T
    }
}