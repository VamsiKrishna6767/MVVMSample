package com.example.mvvmsample.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mvvmsample.*
import com.example.mvvmsample.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Registration : Fragment() {
    private val viewModel by viewModels<RegistrationViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dbu = DataBindingUtil.inflate<FragmentRegistrationBinding>(
            inflater,
            R.layout.fragment_registration,
            container,
            false
        )
        dbu.registration = viewModel
        viewModel.isValid.observe(viewLifecycleOwner) {
            if (it) {
                dbu.progressBar.visibility = View.VISIBLE
                viewModel.saveUser()
            } else {
                dbu.etName.emptyValidation("Please enter name")
                dbu.etAge.emptyValidation("Please enter age")
                dbu.etRegisterMobileNumber.mobileValidation()
                dbu.etRegisterPassword.passwordValidation()
            }
        }
        viewModel.isUserSaved.observe(viewLifecycleOwner) {
            dbu.progressBar.visibility = View.GONE
            if (it) {
                Toast.makeText(
                    requireActivity(),
                    "User registered successfully",
                    Toast.LENGTH_SHORT
                )
                    .show()
                requireActivity().fragment(R.id.layoutContainer, LoginFragment(), null)
            } else Toast.makeText(requireActivity(), "User registration failed", Toast.LENGTH_SHORT)
                .show()
        }
        return dbu.root
    }
}
