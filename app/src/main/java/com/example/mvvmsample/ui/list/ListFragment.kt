package com.example.mvvmsample.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmsample.R
import com.example.mvvmsample.databinding.FragmentListBinding
import com.example.mvvmsample.fragment
import com.example.mvvmsample.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private val viewModel by viewModels<ListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dbu = DataBindingUtil.inflate<FragmentListBinding>(
            inflater,
            R.layout.fragment_list,
            container,
            false
        )
        dbu.listViewModel = viewModel
        viewModel.getAllUsers()
        viewModel.list.observe(viewLifecycleOwner) {
            println("User List :: $it")
            if (it.isNotEmpty()) {
                val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                dbu.usersList.layoutManager = layoutManager
                dbu.usersList.adapter = RecyclerAdapter(requireContext(), it)
            }
            else Toast.makeText(requireContext(), "No Users", Toast.LENGTH_SHORT).show()
        }
        viewModel.logout.observe(viewLifecycleOwner) {
            if (it)
                requireActivity().fragment(R.id.layoutContainer, LoginFragment(), null)
        }
        return dbu.root
    }
}