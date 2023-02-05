package com.projectmar.filmsapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.projectmar.filmsapp.domain.FilmInfo


class FilmsDiffCallback : DiffUtil.ItemCallback<FilmInfo>() {
    override fun areItemsTheSame(oldItem: FilmInfo, newItem: FilmInfo): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: FilmInfo, newItem: FilmInfo): Boolean {
        return oldItem == newItem
    }
}
