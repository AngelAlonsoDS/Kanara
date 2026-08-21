package com.angelalonso.kanara.core.utils

sealed interface InitState {
    data object Loading : InitState
    data object Success : InitState
    data class Error(val message: String) : InitState
}