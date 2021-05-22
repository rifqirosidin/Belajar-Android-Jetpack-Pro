package com.dicoding.movieapp.ui.home.fragment.tv_show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.movieapp.databinding.FragmentTvShowBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TvShowFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
            val tvShows = viewModel.getTvShows()

            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.setTvShows(tvShows)

            with(fragmentTvShowBinding.rvTvShow){
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}