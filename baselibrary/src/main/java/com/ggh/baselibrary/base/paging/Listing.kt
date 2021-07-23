package com.sbingo.wanandroid_mvvm.base.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ggh.baselibrary.base.RequestState


data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val networkState: LiveData<RequestState<Boolean>>,
    val refreshState: LiveData<RequestState<Boolean>>,
    val refresh: () -> Unit,
    val retry: () -> Unit
)
