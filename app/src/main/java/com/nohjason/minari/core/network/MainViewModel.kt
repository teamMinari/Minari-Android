package com.nohjason.myapplication.network

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.ApiService
import com.nohjason.myapplication.network.response.Term
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//class MainViewModel : ViewModel() {
//
//
//    private val _term = MutableStateFlow<Term?>(null)
//    val term: StateFlow<Term?> = _term
//
//    fun fetchTerm(termNm: String, token: String) {
//        viewModelScope.launch {
//            try {
//                val term = api.getOneTerm(termNm = termNm, token = token)
//                _term.value = term
//            } catch (e: Exception) {
//                Log.e("FetchTermError", "Error fetching term: ${e.message}", e)
//            }
//        }
//    }
//}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _term = MutableStateFlow<Term?>(null)
    val term: StateFlow<Term?> = _term

    fun fetchTerm(termNm: String, token: String) {
        viewModelScope.launch {
            try {
                val term = apiService.getOneTerm(termNm = termNm, token = token)
                _term.value = term
            } catch (e: Exception) {
                Log.e("FetchTermError", "Error fetching term: ${e.message}", e)
            }
        }
    }
}
