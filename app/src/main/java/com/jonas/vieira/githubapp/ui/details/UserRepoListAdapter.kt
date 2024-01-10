package com.jonas.vieira.githubapp.ui.details

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jonas.vieira.githubapp.databinding.RowUserRepositoriesBinding
import com.jonas.vieira.githubapp.domain.model.UserRepoModel

class UserRepoListAdapter(private val context: Context) : Adapter<UserRepoListVH>() {

    var repoList: List<UserRepoModel> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(list) {
            field = list
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoListVH {
        val binding =
            RowUserRepositoriesBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserRepoListVH(binding)
    }

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: UserRepoListVH, position: Int) {
        holder.bind(repoList[position])
    }

}

class UserRepoListVH(binding: RowUserRepositoriesBinding) : ViewHolder(binding.root) {
    private val repositoryName = binding.textViewRepositoryName
    private val description = binding.textViewDescription
    private val repoPrivacy = binding.textViewPrivacy
    private val starCount = binding.textViewStar
    private val watchCount = binding.textViewWatch
    private val language = binding.textViewLanguage

    @SuppressLint("SetTextI18n")
    fun bind(repos: UserRepoModel) {
        repositoryName.text = repos.name
        description.text = repos.description
        repoPrivacy.text = repos.visibility
        starCount.text = "Star: ${repos.stargazersCount}"
        watchCount.text = "Watch: ${repos.watchersCount}"
        language.text = repos.language
    }
}
