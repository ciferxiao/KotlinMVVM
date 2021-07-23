package com.ggh.baselibrary.ext

import com.ggh.baselibrary.mvvm.BaseResult


/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：
 * 创建时间：2020/8/20
 * 功能描述：
 *
 * inline :内联类
 *      内联类不能含有 init 代码块
 *      内联类只能含有简单的计算属性（不能含有延迟初始化/委托属性）
 *      参数是一个函数时使用
 *
 *
 *
 */

inline fun <T : Any> BaseResult<T>.checkResult(crossinline onSuccess: (T) -> Unit, crossinline onError: (String?) -> Unit) {
    if (this is BaseResult.Success) {
        onSuccess(data)
    } else if (this is BaseResult.Error) {
        onError(msg)
    }
}

inline fun <T : Any> BaseResult<T>.checkSuccess(success: (T) -> Unit) {
    if (this is BaseResult.Success) {
        success(data)
    }
}