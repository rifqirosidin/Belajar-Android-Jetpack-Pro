package com.dicoding.movieapp.ui.home.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.databinding.FragmentMovieBinding
import com.dicoding.movieapp.ui.home.detail.DetailActivity
import com.dicoding.movieapp.ui.home.favorite.FavoriteActivity
import com.dicoding.movieapp.utils.DataType
import com.dicoding.movieapp.utils.Resource
import com.dicoding.movieapp.utils.Status
import com.dicoding.movieapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            showLoading(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            movieAdapter = MovieAdapter()
            movieViewModel.getMovies().observe(viewLifecycleOwner, movieObserver)

           with(fragmentMovieBinding.rvMovie) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

        fap_favorite_movie.setOnClickListener {
            val intent = Intent(context, FavoriteActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_TYPE, DataType.MOVIE)
            startActivity(intent)
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    movieAdapter.submitList(movies.data)
                    movieAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoading(status: Boolean){
        fragmentMovieBinding.movieProgressBar.isVisible = status
    }
}