package com.gvelesiani.passworx.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gvelesiani.passworx.R
import com.gvelesiani.passworx.base.BaseFragment
import com.gvelesiani.passworx.databinding.FragmentSettingsBinding
import me.ibrahimsn.lib.SmoothBottomBar

class SettingsFragment :
    BaseFragment<SettingsViewModel, FragmentSettingsBinding>(SettingsViewModel::class) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding
        get() = FragmentSettingsBinding::inflate

    override fun setupView(savedInstanceState: Bundle?) {
        requireActivity().findViewById<SmoothBottomBar>(R.id.bottomBar).visibility = View.VISIBLE
    }

    override fun setupObservers() {
    }
}