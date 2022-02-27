package com.gvelesiani.passworx.ui.tools

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gvelesiani.passworx.base.BaseViewModel

class SettingsViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}