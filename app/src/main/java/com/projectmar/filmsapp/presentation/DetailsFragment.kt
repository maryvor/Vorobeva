package com.projectmar.filmsapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projectmar.filmsapp.R
import com.projectmar.filmsapp.ShowFragment
import com.projectmar.filmsapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val viewModel by lazy { ViewModelProvider(this)[DetailsViewModel::class.java] }

    private lateinit var filmId: String

    private var showFragment: ShowFragment = ShowFragment.Empty()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragment = (context as ShowFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmId = requireArguments().getString(FILM_ID_KEY).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.item.observe(viewLifecycleOwner) {
            val genresTemplate = requireContext().getString(R.string.details_genres_template)
            val countriesTemplate = requireContext().getString(R.string.countries_template)
            val yearTemplate = requireContext().getString(R.string.details_year_template)
            binding.title.text = it.name
            binding.year.text = String.format(yearTemplate, it.year)
            binding.description.text = it.description
            binding.genres.text = String.format(genresTemplate, it.genres)
            binding.countries.text = String.format(countriesTemplate, it.countries)
            Glide.with(this).load(it.banner).apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
                .into(binding.banner)
        }
        viewModel.getDetails(filmId)

        viewModel.showProgress.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        binding.errorRepeatButton.setOnClickListener {
            viewModel.getDetails(filmId)
        }
        viewModel.showError.observe(viewLifecycleOwner) {
            val visibility = if (it) View.VISIBLE else View.INVISIBLE
            binding.errorImg.visibility = visibility
            binding.errorTv.visibility = visibility
            binding.errorRepeatButton.visibility = visibility
            binding.banner.visibility = if (!it) View.VISIBLE else View.INVISIBLE
        }

        binding.detailsToolbar.inflateMenu(R.menu.details_menu)
        binding.detailsToolbar.setNavigationIcon(R.drawable.arrow_back)
        binding.detailsToolbar.setNavigationOnClickListener { showFragment.popBackStack() }

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
    override fun onDetach() {
        super.onDetach()
        showFragment = ShowFragment.Empty()
    }
}