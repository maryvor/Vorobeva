package com.projectmar.filmsapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.projectmar.filmsapp.ShowFragment
import com.projectmar.filmsapp.databinding.FragmentListBinding
import com.projectmar.filmsapp.domain.FilmInfo
import com.projectmar.filmsapp.presentation.adapters.FilmsRvAdapter
import kotlinx.coroutines.launch


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val viewModel by lazy { ViewModelProvider(this)[FilmsViewModel::class.java] }

    private var showFragment: ShowFragment = ShowFragment.Empty()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragment = (context as ShowFragment)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = FilmsRvAdapter(requireContext(), object : FilmsRvAdapter.OnFilmClickListener {
            override fun onFilmClick(filmInfo: FilmInfo) {
                showFragment.showFragment(DetailsFragment.newInstance(filmInfo.id))
            }
        })
        binding.mainListRv.adapter = adapter

        viewModel.init()

        viewModel.lifeData.observe(viewLifecycleOwner) {
            if (it != null)
                lifecycleScope.launch { adapter.submitData(it) }

        }
        viewModel.showProgress.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDetach() {
        super.onDetach()
        showFragment = ShowFragment.Empty()
    }

    companion object {

        fun newInstance() = ListFragment()
    }
}