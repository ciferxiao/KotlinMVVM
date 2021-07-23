package com.ggh.baselibrary.bean

import com.ggh.baselibrary.MyConfig
import com.ggh.baselibrary.utils.SPUtils


class UserPreference {
    companion object{
        fun saveUser(userBean: UserBean){
            SPUtils.putValue(MyConfig.userName,userBean.userName)
            SPUtils.putValue(MyConfig.SP_MY_NICKNAME,userBean.nickName)
            SPUtils.putValue(MyConfig.SP_MY_UID,userBean.myUid)
            SPUtils.putValue(MyConfig.SP_ACCESS_TOKEN,userBean.accessToken)
            SPUtils.putValue(MyConfig.SP_COMMUNI_CHANNEL,userBean.cloud)
            SPUtils.putValue(MyConfig.SP_COMMUNI_CHANNEL_OPEN,userBean.cloudOpen)
            SPUtils.putValue(MyConfig.SP_COMMUNI_PTT_OPEN,userBean.pttOpen)
        }

        fun getUser() : UserBean {
            val userBean : UserBean? = UserBean()
            userBean?.accessToken = SPUtils.SP.getString(MyConfig.userName,"感悟")
            userBean?.nickName = SPUtils.SP.getString(MyConfig.SP_MY_NICKNAME,"感悟")
            userBean?.myUid = SPUtils.SP.getInt(MyConfig.SP_MY_UID,0)
            userBean?.accessToken = SPUtils.SP.getString(MyConfig.SP_ACCESS_TOKEN,"")
            userBean?.cloud = SPUtils.SP.getInt(MyConfig.SP_COMMUNI_CHANNEL,0)
            userBean?.cloudOpen = SPUtils.SP.getBoolean(MyConfig.SP_COMMUNI_CHANNEL_OPEN,true)
            userBean?.pttOpen = SPUtils.SP.getBoolean(MyConfig.SP_COMMUNI_PTT_OPEN,false)

            return userBean!!
        }
    }

}