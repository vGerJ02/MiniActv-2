package com.example.android.whileinuselocation.UI

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForegroundLocationViewModel : ViewModel() {
    private val _buttonText = MutableLiveData("Start Location Updates")
    val buttonText: LiveData<String> = _buttonText

    private val _outputText = MutableLiveData<String>("")
    val outputText: LiveData<String> = _outputText

    fun setButtonText(text: String) {
        _buttonText.value = text
    }


    fun appendToOutputText(newText: String) {
        _outputText.value = "${_outputText.value}$newText\n"
    }

    private val _snackbarMessage = MutableLiveData(false)
    val snackbarMessage: LiveData<Boolean> = _snackbarMessage

    fun showSnackbar(state: Boolean) {
        _snackbarMessage.value = state
    }


    private val _showSnackbar = MutableLiveData<Boolean>()
    private val _snackbarText = MutableLiveData<String>()
    private val _snackbarActionLabel = MutableLiveData<String>()
    val showSnackbar: LiveData<Boolean> = _showSnackbar
    val snackbarText: LiveData<String> = _snackbarText
    val snackbarActionLabel: LiveData<String> = _snackbarActionLabel
    var action = {}
    var dismissed = {}

    fun triggerSnackbar(
        text: String,
        actionLabel: String,
        onActionPerformed: () -> Unit = {},
        onDismissed: () -> Unit = {}
    ) {
        _snackbarText.value = text
        _snackbarActionLabel.value = actionLabel
        action = onActionPerformed
        dismissed = onDismissed
        _showSnackbar.value = true
    }

    fun resetSnackbar() {
        action = {}
        dismissed = {}
        _showSnackbar.value = false
    }

}