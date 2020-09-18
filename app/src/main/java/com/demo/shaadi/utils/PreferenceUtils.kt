package com.demo.shaadi.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PreferenceUtils {

    private var mSettings: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null

    companion object{
        const val APP_PREF = "SHAADI_PREF"
        private var sInstance: PreferenceUtils? = null

        fun getInstance(context: Context): PreferenceUtils {
            if (sInstance == null) {
                sInstance = PreferenceUtils(context)
            }
            return sInstance as PreferenceUtils
        }
    }
    constructor()

    @SuppressLint("CommitPrefEdits")
    constructor(mContext: Context?) {
        mSettings = mContext?.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
        mEditor = mSettings?.edit()
    }

    fun getValue(key: String, defValue: Boolean): Boolean {
        return mSettings!!.getBoolean(key, defValue)
    }

    fun setValue(key: String, value: Boolean) {
        mEditor!!.putBoolean(key, value)
        mEditor!!.apply()
    }
}