package com.nohjason.myapplication.network

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.response.BookResponse
import com.nohjason.minari.screens.profile.ProfileResponse
import com.nohjason.myapplication.network.RetrofitInstance.api
import com.nohjason.myapplication.network.response.Term
import com.nohjason.myapplication.network.response.TermResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _profileData = MutableStateFlow<ProfileResponse?>(null)
    val profileData: StateFlow<ProfileResponse?> = _profileData

    fun fetchProfileData(userId: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) { RetrofitInstance.api.getProfile() }
                Log.d("MainViewModel", "fetchProfileData success: $response")
                _profileData.value = response
            } catch (e: Exception) {
                Log.e("MainViewModel", "fetchProfileData failed with error: ${e.message}", e)
            }
        }
    }

    private val _term = MutableStateFlow<Term?>(null)
    val term: StateFlow<Term?> = _term

    fun fetchTerm(termNm: String) {
        viewModelScope.launch {
            try {
                val term = api.getOneTerm(termNm = termNm)
                _term.value = term
            } catch (e: Exception) {
                // 오류 처리
            }
        }
    }

//    private val _book = MutableStateFlow<List<DictionaryModel>>(emptyList())
//    val books = _book.asStateFlow()

//    fun fetchAllBookTerms() {
//        viewModelScope.launch {
//            try {
//                val book = RetrofitInstance.api.getBookTerms()
//                _book.value = book.data.map {
//                    it.toModel()
//                }
//            } catch (e: Exception) {
//                // Handle error
//                Log.e("TAG", "fetch ALL", e)
//            }
//        }
//    }

//    fun addDelete(word: String) {
//        viewModelScope.launch {
//            try {
//                val job = async { RetrofitInstance.api.addDeleteTerm(word) }
//                job.await()
//                fetchAllBookTerms()
//            } catch (e: Exception) {
//                Log.e("TAG", "addDelete: ", e)
//            }
//        }
//    }

//    fun checkedThat(wordNm: String) = viewModelScope.launch {
//        _book.update {
//            it.map { item ->
//                if (item.termNm == wordNm) {
//                    item.copy(
//                        isChecked = item.isChecked.not()
//                    )
//                } else {
//                    item
//                }
//            }
//        }
//    }
}