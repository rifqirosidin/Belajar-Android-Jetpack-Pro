package com.dicoding.movieapp.ui.home.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.movieapp.databinding.FragmentFavoriteTvShowBinding
import com.dicoding.movieapp.viewmodel.ViewModelFactory


class FavoriteTvShowFragment : Fragment() {

    private lateinit var fragmentFavoriteTvShowBinding: FragmentFavoriteTvShowBinding
    private val binding get() = fragmentFavoriteTvShowBinding

    private lateinit var viewModel: FavoriteTvShowViewModel
    private lateinit var favoriteTvShowAdapter: FavoriteTvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFavoriteTvShowBinding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, Observer { tvShows ->
            if (tvShows != null) {
                favoriteTvShowAdapter.submitList(tvShows)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

            favoriteTvShowAdapter = FavoriteTvShowAdapter()

            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, Observer { favTvShow ->
                if (favTvShow != null) {
                    favoriteTvShowAdapter.submitList(favTvShow)
                    showLoading(false)
                }
            })

            with(fragmentFavoriteTvShowBinding.rvFavoriteTvShow) {
                layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = favoriteTvShowAdapter
            }
        }
    }
    private fun showLoading(status: Boolean){
        fragmentFavoriteTvShowBinding.progressBarFavoriteTvShow.isVisible = status
    }
}