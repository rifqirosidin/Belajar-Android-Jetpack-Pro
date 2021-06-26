package com.dicoding.movieapp.ui.home.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.movieapp.databinding.FragmentFavoriteMovieBinding
import com.dicoding.movieapp.viewmodel.ViewModelFactory


class FavoriteMovieFragment : Fragment() {

    private lateinit var fragmentMovieFavoriteBinding: FragmentFavoriteMovieBinding
    private val binding get() = fragmentMovieFavoriteBinding

    private lateinit var viewModel: FavoriteMovieViewModel
    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMovieFavoriteBinding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, Observer { favMovies ->
            if (favMovies != null) {
                favoriteMovieAdapter.submitList(favMovies)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            favoriteMovieAdapter = FavoriteMovieAdapter()

            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, Observer { favMovies ->
                if (favMovies != null) {
                    favoriteMovieAdapter.submitList(favMovies)
                    showLoading(false)
                }
            })

            with(fragmentMovieFavoriteBinding.rvFavoriteMovie) {
                layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = favoriteMovieAdapter
            }
        }
    }
    private fun showLoading(status: Boolean){
        fragmentMovieFavoriteBinding.progressBarFavoriteMovie.isVisible = status
    }
}

