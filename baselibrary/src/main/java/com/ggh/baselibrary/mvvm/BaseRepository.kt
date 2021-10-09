package com.ggh.baselibrary.mvvm

import com.ggh.baselibrary.Config
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * 文件名：BaseRepository
 * 创建时间：2020/8/8
 * 功能描述：  open 表示可以被继承   默认不可被继承
 *
 * 用来管理Api
 */
open class BaseRepository {

    /**
     * 返回码处理
     */
    suspend fun <T : Any> executeResponse(response: ResponseData<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
                                          errorBlock: (suspend CoroutineScope.() -> Unit)? = null): BaseResult<T> {
        return coroutineScope {

            val code = response.code

            if (code == Config.CODE_SUCCESS){
                //请求成功返回数据
                successBlock?.let { it() }
                BaseResult.Success(response.data)
            }else {
                errorBlock?.let { it() }
                //请求失败返回  code   msg 用作显示与打印日志
                BaseResult.Error(code,response.msg)
            }

        }
    }

}