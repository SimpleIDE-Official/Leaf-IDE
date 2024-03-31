package com.mucheng.leafide.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class SetupPage {
    WelcomePage,
    PermissionReuqestPage,
    LauncherOptionsPage
}

class SetupViewModel : ViewModel() {

    private val pageIndexMutableStateFlow = MutableStateFlow(SetupPage.WelcomePage)

    val pageStateFlow = pageIndexMutableStateFlow.asStateFlow()

    fun gotoNext() {
        viewModelScope.launch {
            when (pageIndexMutableStateFlow.value) {
                SetupPage.WelcomePage -> {
                    pageIndexMutableStateFlow.value = SetupPage.PermissionReuqestPage
                }
                SetupPage.PermissionReuqestPage -> {
                    pageIndexMutableStateFlow.value = SetupPage.LauncherOptionsPage
                }

                SetupPage.LauncherOptionsPage -> {

                }
            }
        }
    }

    fun gotoPrev() {
        viewModelScope.launch {
            when (pageIndexMutableStateFlow.value) {
                SetupPage.WelcomePage -> {

                }
                SetupPage.PermissionReuqestPage -> {
                    pageIndexMutableStateFlow.value = SetupPage.WelcomePage
                }

                SetupPage.LauncherOptionsPage -> {
                    pageIndexMutableStateFlow.value = SetupPage.PermissionReuqestPage
                }
            }
        }
    }

    fun canGotoPrev(): Boolean {
        return pageIndexMutableStateFlow.value != SetupPage.WelcomePage
    }

    fun canGotoNext(): Boolean {
        return pageIndexMutableStateFlow.value != SetupPage.LauncherOptionsPage
    }

}