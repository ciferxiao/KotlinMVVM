package com.ggh.model_message

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessageViewModel : ViewModel() {

    val nameList = MutableLiveData<List<String>>()
    val nameListResult:LiveData<List<String>> = nameList

    fun getNames(){
        viewModelScope.launch {
            nameList.value =  getNameList()
        }
    }


    suspend fun getNameList():List<String>{
        return withContext(Dispatchers.IO){
            listOf("111","222")
        }

    }


}