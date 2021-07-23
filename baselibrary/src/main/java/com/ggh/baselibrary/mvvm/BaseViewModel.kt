package com.ggh.baselibrary.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel(){

    open class UiState<T>(
        val showLoading: Boolean = false,//显示加载框
        val isSuccess: T? = null,//加载成功数据
        val isError: String?= null,//加载失败数据
        val isRefresh: Boolean = false,//是否刷新
        val isLoading: Boolean = false//是否加载


    )

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }
}