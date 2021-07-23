package com.ggh.baselibrary

import com.alibaba.fastjson.JSON
import com.ggh.baselibrary.bean.LoginInfoBean
import com.ggh.baselibrary.utils.SPUtils

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 创建时间：2020/10/21
 * 功能描述：
 */
object ConfigInfo {

    val TOKEN_KEY = "satoken"
    val TOKEN_VALUE = "token"
    val LOGIN_INFO = "loginInfo"
    val USER_INFO_ID = "userId"


    /**
     * 添加token 信息
     */
    fun setTokenInfo(key:String,value:String,loginInfo:LoginInfoBean){
        SPUtils.putValue(TOKEN_KEY,key)
        SPUtils.putValue(TOKEN_VALUE,value)
        SPUtils.putValue(LOGIN_INFO,JSON.toJSONString(loginInfo))
        SPUtils.putValue(USER_INFO_ID,loginInfo.id)
    }
    //获取token  key
    fun getTokenKey():String = SPUtils.getValue(TOKEN_KEY,"satoken")
    //获取token  value
    fun getTokenValue():String = SPUtils.getValue(TOKEN_VALUE,"1")
    //获取token  value
    fun getLoginInfo():String = SPUtils.getValue(LOGIN_INFO,"1")

    fun getUserId():String = SPUtils.getValue(USER_INFO_ID,"1")

}