package com.ggh.baselibrary.base.adapter

import com.umeng.commonsdk.debug.D

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 创建时间：2020/11/4
 * 功能描述：  多种类型的 item 外壳
 */
data class MultipleBean<T> (
    val viewType:EnumImp,    //视图类型ID
    val title:String?="",    //视图标题  可做描述作用
    val t:T? = null                  //数据
):ListBean