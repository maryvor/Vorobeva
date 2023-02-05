package com.projectmar.filmsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.projectmar.filmsapp.R
import com.projectmar.filmsapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val viewModel by lazy { ViewModelProvider(this)[FilmsViewModel::class.java] }

    private lateinit var filmId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmId = requireArguments().getString(FILM_ID_KEY).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel.item.observe(viewLifecycleOwner) {
            val genresTemplate = requireContext().getString(R.string.details_genres_template)
            val countriesTemplate = requireContext().getString(R.string.countries_template)
            binding.title.text = it.name
            binding.year.text = it.year
            binding.description.text = it.description
            binding.genres.text = String.format(genresTemplate, it.genres)
            binding.countries.text = String.format(countriesTemplate, it.countries)
            Glide.with(requireContext()).load(it.banner).into(binding.banner)
        }


        viewModel.getDetails(filmId)

        viewModel.showProgress.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        return binding.root
    }

    companion object {
        private const val FILM_ID_KEY = "filmId"

        fun newInstance(filmId: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(FILM_ID_KEY, filmId)
                }
            }
    }
}