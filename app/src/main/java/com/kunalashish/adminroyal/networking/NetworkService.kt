package com.kunalashish.adminroyal.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.kunalashish.adminroyal.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService
{
    val networkInstance : NetworkCallsInterface
    init {
        val retrofit = Retrofit.Builder().baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        networkInstance = retrofit.create(NetworkCallsInterface::class.java)
    }
    fun checkForInternet(context : Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo?=connectivityManager.activeNetworkInfo
        return if(activeNetwork?.isConnected!=null){
            activeNetwork.isConnected
        }else{
            false
        }
    }
}