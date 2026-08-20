package com.angelalonso.kanara.utils

sealed interface InitState {
    data object Loading : InitState
    data object Success : InitState
    data class Error(val message: String) : InitState
}