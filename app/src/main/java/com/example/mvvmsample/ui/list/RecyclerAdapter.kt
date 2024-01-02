package com.example.mvvmsample.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsample.R
import com.example.mvvmsample.data.responses.User
import com.example.mvvmsample.databinding.ListItemBinding

// this recycler adapter is for showing list of users in the ui.
class RecyclerAdapter(private val context: Context, private val users: List<User>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dbu = DataBindingUtil.inflate<ListItemBinding>(
            LayoutInflater.from(context),
            R.layout.list_item,
            parent,
            false
        )
        return ViewHolder(dbu)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showUsers(users[position])
    }

    class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun showUsers(user: User) {
            binding.itemViewModel = ListItemViewModel(user)
        }
    }
}