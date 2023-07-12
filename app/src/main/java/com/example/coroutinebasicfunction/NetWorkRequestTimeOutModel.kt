package com.example.coroutinebasicfunction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import java.lang.Exception

class NetWorkRequestTimeOutModel:ViewModel() {

    private fun usingWithOut(timeOut:Long){
        viewModelScope.launch {
            try{
                val recentAndroidVersions = withTimeout(timeOut){
                    //  mockApi.getRecentAndroidVersions();
                }
            }catch(timeoutCancellationException: TimeoutCancellationException){
              //  uiState.value = UiState.error("network request timeout")
            }catch(exception:Exception){
                //  uiState.value = UiState.error("network request falied")
            }

        }
    }

    private fun usingWithOutOrNull(timeOut:Long){
        viewModelScope.launch {
            try{
                val recentAndroidVersions = withTimeoutOrNull(timeOut){
                    //  mockApi.getRecentAndroidVersions();
                }
                if(recentAndroidVersions != null){
                      //  uiState.value = UiState.Sucess(recentAndroidVersions)
                }else{
                    //  uiState.value = UiState.Error("network request timeout")
                }
            }catch(exception:Exception){
                //  uiState.value = UiState.Error("network request falied")
            }

        }
    }
}