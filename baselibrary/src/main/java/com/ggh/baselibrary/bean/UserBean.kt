package com.ggh.baselibrary.bean

import com.senseplay.collectsdk.model.account.AccountInfo


class UserBean {

    var nickName: String? = null
    var userName: String? = null
    var language = false
    var myUid = 0
    var leapId = 0
    var gender = 0
    var accessToken: String? = null
    var cloud = 0

    var cloudOpen = false
    var pttOpen = false
    var cacheFriendMap: Map<Int, AccountInfo>? = null



}