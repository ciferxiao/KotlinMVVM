package com.ggh.baselibrary.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 文件名：RecyclerViewModel
 * 创建时间：2020/8/14
 * 功能描述：  列表通用ViewModel
 */
abstract class RecyclerViewModel<RecyclerModel> : BaseViewModel() {
    var mPage:MutableLiveData<Int> = MutableLiveData()
    var mLimit:MutableLiveData<Int> = MutableLiveData()

    init {
        mLimit.value = 10
        mPage.value = 1
    }
    private val _uiState = MutableLiveData<UiState<List<RecyclerModel>>>()
    //返回下面检查后的状态
    val uiState: LiveData<UiState<List<RecyclerModel>>> get() = _uiState

    open fun load(isRefresh: Boolean = true){
        viewModelScope.launch (Dispatchers.Main){
            //加载数据的时候开启加载框
            checkUiState(true)
            val data = setDataParameter()
            if (data is BaseResult.Success) {
                val list = data.data
                //加载成功   取消加载框，
                checkUiState(showLoading = false, isSuccess = list)
            } else if (data is BaseResult.Error) {
                checkUiState(showLoading = false, isError = data.msg)
            }
        }
    }

    //设置参数
    abstract suspend fun setDataParameter(): BaseResult<List<RecyclerModel>>

    private fun checkUiState(
        showLoading: Boolean = false,//是否显示加载框    true=显示   false=隐藏
        isSuccess: List<RecyclerModel>? = null,//成功的数据
        isError: String? = null,//显示错误
        isLoading: Boolean = false,//没有更多数据
        isRefresh: Boolean = false//是否刷新
    ) {
        _uiState.value = UiState(showLoading, isSuccess, isError, isLoading, isRefresh)
    }
}