package com.ggh.model_login.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ggh.baselibrary.mvvm.BaseViewModel
import com.senseplay.collectsdk.AccountManager
import com.senseplay.collectsdk.model.account.AccountBean
import com.senseplay.mframe.client.MCallBack

class LoginViewModel : BaseViewModel() {

    var accountBean = MutableLiveData<AccountBean>()

    fun login() {
        AccountManager.getInstance().login(
            "", "", ""
        ) {
            it.let {
                accountBean.value = it

            }
        }

        launchOnUI { accountBean }
    }

}