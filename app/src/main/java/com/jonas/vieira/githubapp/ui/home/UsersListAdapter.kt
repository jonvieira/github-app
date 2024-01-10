package com.jonas.vieira.githubapp.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.jonas.vieira.githubapp.databinding.RowUsersListBinding
import com.jonas.vieira.githubapp.domain.model.UsersModel
import com.jonas.vieira.githubapp.ui.core.setImage
import com.jonas.vieira.githubapp.ui.home.UsersListAdapter.UsersListVH

class UsersListAdapter(
    private val context: Context,
    private val onClickListener: OnClickListener
) : Adapter<UsersListVH>() {

    var usersList: List<UsersModel> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(list) {
            field = list
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListVH {
        val binding = RowUsersListBinding.inflate(LayoutInflater.from(context), parent, false)
        return UsersListVH(binding)
    }

    override fun getItemCount() = usersList.size

    override fun onBindViewHolder(holder: UsersListVH, position: Int) {
        val user = usersList[position]

        holder.itemView.setOnClickListener { onClickListener.onClick(user) }
        holder.bind(user)
    }


    class UsersListVH(binding: RowUsersListBinding) : ViewHolder(binding.root) {
        private val userNameAvatar = binding.imageViewUserAvatar
        private val userNameText = binding.textViewUserName
        private val userUrlText = binding.textViewUserUrl

        fun bind(users: UsersModel) {
            userNameAvatar.setImage(users.avatarUrl)
            userNameText.text = users.login
            userUrlText.text = users.htmlUrl
        }
    }

    class OnClickListener(val clickListener: (user: UsersModel) -> Unit) {
        fun onClick(user: UsersModel) = clickListener(user)
    }
}
