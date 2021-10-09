package com.ggh.model_login.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ggh.baselibrary.mvvm.BaseViewModel
import com.senseplay.collectsdk.AccountManager
import com.senseplay.collectsdk.model.account.AccountBean

class LoginViewModel : BaseViewModel() {

    var accountBean = MutableLiveData<AccountBean>()

    fun getAccountBean(name: String, pwd: String, qrCode: String): LiveData<AccountBean> {
        //login(name, pwd, qrCode)
        Log.d("xiao111", " accountBean $accountBean")

        return accountBean
    }

    fun login(name: String, pwd: String, qrCode: String) {
        AccountManager.getInstance().login(
            name, "123456", qrCode
        ) {
            it.let {
                accountBean.value = it
            }
        }

        launchOnUI { accountBean }
    }

}