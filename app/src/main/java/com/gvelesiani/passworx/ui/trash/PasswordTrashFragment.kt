package com.gvelesiani.passworx.ui.trash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.gvelesiani.passworx.R
import com.gvelesiani.passworx.base.BaseFragment
import com.gvelesiani.passworx.data.models.PasswordModel
import com.gvelesiani.passworx.databinding.FragmentPasswordsBinding
import com.gvelesiani.passworx.ui.passwords.adapter.PasswordAdapter
import me.ibrahimsn.lib.SmoothBottomBar

class PasswordTrashFragment :
    BaseFragment<PasswordTrashViewModel, FragmentPasswordsBinding>(PasswordTrashViewModel::class) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPasswordsBinding =
        FragmentPasswordsBinding::inflate

    private lateinit var adapter: PasswordAdapter

    override fun setupView(savedInstanceState: Bundle?) {
        requireActivity().findViewById<SmoothBottomBar>(R.id.bottomBar).visibility = View.GONE
        binding.btAddPassword.visibility = View.GONE
        viewModel.getPasswords(true)
        setupRecyclerViewAdapter()
    }

    override fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, {
            observeViewState(it)
        })
    }

    private fun observeViewState(viewState: PasswordTrashViewModel.ViewState) {
        adapter.submitData(viewState.passwords)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showMenu(v: View, @MenuRes menuRes: Int, password: PasswordModel, position: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.menuEditAndRestore -> Toast.makeText(requireContext(), "Edit", Toast.LENGTH_SHORT).show()
                R.id.menuDeletePermanently -> {
                    viewModel.deletePassword(passwordId = password.passwordId)
                    adapter.notifyItemChanged(position)
                    adapter.notifyDataSetChanged()
                }
            }
            true
        }
        popup.setOnDismissListener {
        }
        popup.show()
    }

    private fun setupRecyclerViewAdapter() {
        adapter = PasswordAdapter(
            clickListener = { password: PasswordModel ->
                Toast.makeText(requireContext(), password.emailOrUserName, Toast.LENGTH_SHORT)
                    .show()
            },
            menuClickListener = { password: PasswordModel, view: View, position: Int ->// it == PasswordModel
                showMenu(
                    view,
                    R.menu.trashed_passwords_menu,
                    password,
                    position
                )
            })
        binding.rvPasswords.adapter = adapter
        binding.rvPasswords.layoutManager = LinearLayoutManager(requireContext())
    }
}