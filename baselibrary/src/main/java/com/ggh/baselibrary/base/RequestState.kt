package com.ggh.baselibrary.base

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：
 * 创建时间：2020/8/10
 * 功能描述： 网络请求状态
 */
enum class Status {
    LOADING,
    SUCCESS,
    ERROR,
}

data class RequestState<out T>(val status: Status, val data: T?, val message: String? = null) {
    companion object {
        fun <T> loading(data: T? = null) = RequestState(Status.LOADING, data)

        fun <T> success(data: T? = null) = RequestState(Status.SUCCESS, data)

        fun <T> error(msg: String? = null, data: T? = null) = RequestState(Status.ERROR, data, msg)
    }

    fun isLoading(): Boolean = status == Status.LOADING
    fun isSuccess(): Boolean = status == Status.SUCCESS
    fun isError(): Boolean = status == Status.ERROR
}
