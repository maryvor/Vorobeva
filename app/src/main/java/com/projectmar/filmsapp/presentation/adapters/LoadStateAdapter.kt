package com.projectmar.filmsapp.presentation.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projectmar.filmsapp.R


class LoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadStateViewHolder(parent, retry)

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)
}

class LoadStateViewHolder(
    parent: ViewGroup,
    retry: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.error_item, parent, false)
) {
    private val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
    private val errorMsg: TextView = itemView.findViewById(R.id.error_tv)
    private val errorImg: ImageView = itemView.findViewById(R.id.error_img)
    private val retry: Button = itemView.findViewById<Button>(R.id.error_repeat_button)
        .also { it.setOnClickListener { retry.invoke() } }

    fun bind(loadState: LoadState) {
        progressBar.visibility = toVisibility(loadState is LoadState.Loading)
        retry.visibility = toVisibility(loadState is LoadState.Error)
        errorMsg.visibility = toVisibility(loadState is LoadState.Error)
        errorImg.visibility = toVisibility(loadState is LoadState.Error)

    }

    private fun toVisibility(constraint: Boolean): Int = if (constraint) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
