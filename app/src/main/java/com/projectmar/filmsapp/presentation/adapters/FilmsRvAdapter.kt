package com.projectmar.filmsapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projectmar.filmsapp.R
import com.projectmar.filmsapp.databinding.ListItemBinding
import com.projectmar.filmsapp.domain.FilmInfo


class FilmsRvAdapter(
    private val context: Context,
    private val onFilmClickListener: OnFilmClickListener
) :
    PagingDataAdapter<FilmInfo, FilmsRvAdapter.FilmInfoViewHolder>(FilmsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmInfoViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return FilmInfoViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FilmInfoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

    }

    inner class FilmInfoViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: FilmInfo) {
            with(binding) {
                val genresYearsTemplate =
                    context.resources.getString(R.string.list_item_genres_year_template)
                this.filmTitle.text = film.name
                this.genresYear.text = String.format(genresYearsTemplate, film.genres, film.year)
                Glide.with(context).load(film.smallImg).apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                ).into(filmImage)
                this@FilmInfoViewHolder.itemView.setOnClickListener {
                    onFilmClickListener.onFilmClick(film)
                }
                this.isFavouriteSmb.visibility =
                    if (film.isFavourite) View.VISIBLE else View.INVISIBLE
            }
        }
    }

    interface OnFilmClickListener {
        fun onFilmClick(filmInfo: FilmInfo)
    }
}