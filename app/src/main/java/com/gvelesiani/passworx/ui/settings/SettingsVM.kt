package com.gvelesiani.passworx.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gvelesiani.passworx.base.BaseViewModel
import com.gvelesiani.passworx.domain.useCases.GetMasterPasswordUseCase
import com.gvelesiani.passworx.ui.masterPassword.createMasterPassword.CreateMasterPasswordVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SettingsVM(private val getMasterPasswordUseCase: GetMasterPasswordUseCase) : BaseViewModel() {

    val viewState: MutableLiveData<ViewState> = MutableLiveData()

    init {
        viewState.value = ViewState()
    }

    private fun currentViewState(): ViewState = viewState.value!!

    fun getMasterPassword(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if(getMasterPasswordUseCase.run(Unit) == ""){
                    viewState.postValue(currentViewState().copy(masterPasswordExists = false))
                } else {
                    viewState.postValue(currentViewState().copy(masterPasswordExists = true))
                }
            } catch (ignored: Exception){}
        }
    }

    data class ViewState(
        val masterPasswordExists: Boolean? = null
    )
}