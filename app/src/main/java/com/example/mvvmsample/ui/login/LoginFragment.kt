package com.example.mvvmsample.ui.login

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mvvmsample.R
import com.example.mvvmsample.databinding.FragmentLoginBinding
import com.example.mvvmsample.fragment
import com.example.mvvmsample.mobileValidation
import com.example.mvvmsample.passwordValidation
import com.example.mvvmsample.ui.list.ListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dbu = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        dbu.loginViewModel = viewModel

        viewModel.isValid.observe(viewLifecycleOwner) {
            if (it) {
                dbu.progressBar.visibility = View.VISIBLE
                viewModel.getUser()
            } else {
                dbu.etLoginMobileNumber.mobileValidation()
                dbu.etLoginPassword.passwordValidation()
            }
        }

        viewModel.isUserValid.observe(viewLifecycleOwner) {
            dbu.progressBar.visibility = View.GONE
            if (it) {
                toast("Login successfully")
                requireActivity().fragment(R.id.layoutContainer, ListFragment(), null)
            } else toast("Mobile number or Password invalid")
        }

        viewModel.isSignUpClicked.observe(viewLifecycleOwner) {
            if (it) {
                requireActivity().fragment(R.id.layoutContainer, Registration(), "Registration")
                viewModel.isSignUpClicked.value = false
            }
        }

        return dbu.root
    }

    private fun toast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}