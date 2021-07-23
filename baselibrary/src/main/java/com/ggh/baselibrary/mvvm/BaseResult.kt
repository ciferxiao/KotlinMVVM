package com.ggh.baselibrary.mvvm

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：
 * 创建时间：2020/8/20
 * 功能描述： 检查返回的结果
 *
 *    sealed：标识一个密封类     强制要求将每一个所会出现的情况都表示出来，做处理
 *
 */
sealed class BaseResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : BaseResult<T>()
    data class Error(val code:Int,val msg :String) : BaseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$msg]"
        }
    }
}