package com.demo.shaadi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.demo.shaadi.utils.ConnectionLiveData
import com.demo.shaadi.utils.PreferenceUtils
import com.zplesac.connectionbuddy.ConnectionBuddy
import com.zplesac.connectionbuddy.models.ConnectivityEvent
import com.zplesac.connectionbuddy.models.ConnectivityState

abstract class MyBaseFragment : Fragment() {

    private var isConnected: Boolean = false
    private var connectivityEvent: ConnectivityEvent = ConnectivityEvent()
    private var mConnectionLiveData = ConnectionLiveData()
    var mPreferenceUtils: PreferenceUtils = PreferenceUtils()

    abstract fun onNetworkChange(isConnected: Boolean)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLiveConnection(savedInstanceState)
        initPreference()
    }

    private fun initPreference() {
        context?.run {
            mPreferenceUtils = PreferenceUtils.getInstance(this)
        }
    }

    // To check Internet connection
    private fun initLiveConnection(savedInstanceState: Bundle?) {
        isConnected = ConnectionBuddy.getInstance().hasNetworkConnection()
        mConnectionLiveData.run {
            init(savedInstanceState != null)
                .observe(this@MyBaseFragment, Observer { connection ->
                    // Checks if the connection variable is not null then only execute the block.
                    connection?.let {

                        connectivityEvent = connection
                        isConnected = connection.state.value == ConnectivityState.CONNECTED
                        // Convey the network change event to current Activity.
                        onNetworkChange(isConnected)
                    }
                })
        }

    }

    override fun onStart() {
        super.onStart()
        mConnectionLiveData.registerForNetworkUpdates()
    }

    override fun onStop() {
        super.onStop()
        mConnectionLiveData.unregisterFromNetworkUpdates()
    }
}