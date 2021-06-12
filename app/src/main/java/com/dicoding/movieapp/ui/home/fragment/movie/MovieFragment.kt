package com.dicoding.movieapp.ui.home.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.movieapp.databinding.FragmentMovieBinding
import com.dicoding.movieapp.viewmodel.ViewModelFactory

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            showLoading(true)

            val factory = ViewModelFactory.getInstance()
            movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()
            movieViewModel.getMovies().observe(viewLifecycleOwner, Observer { listMovie ->
                showLoading(false)
                movieAdapter.setMovie(listMovie)
                movieAdapter.notifyDataSetChanged()
            })

           with(fragmentMovieBinding.rvMovie) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun showLoading(status: Boolean){
        fragmentMovieBinding.movieProgressBar.isVisible = status;
    }
}