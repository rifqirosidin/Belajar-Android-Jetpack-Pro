package com.dicoding.movieapp.ui.home.fragment.tv_show

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
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.databinding.FragmentTvShowBinding
import com.dicoding.movieapp.ui.home.detail.DetailActivity
import com.dicoding.movieapp.ui.home.favorite.FavoriteActivity
import com.dicoding.movieapp.utils.DataType
import com.dicoding.movieapp.utils.Resource
import com.dicoding.movieapp.utils.Status
import com.dicoding.movieapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            showLoading(true)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            tvShowAdapter = TvShowAdapter()
            viewModel.getTvShows().observe(viewLifecycleOwner, tvShowObserver)

            with(fragmentTvShowBinding.rvTvShow){
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }

        fap_favorite_tv_show.setOnClickListener {
            val intent = Intent(context, FavoriteActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_TYPE, DataType.TV_SHOW)
            startActivity(intent)
        }
    }
    private val tvShowObserver = Observer<Resource<PagedList<TvShowEntity>>> { tvShow ->
        if (tvShow != null) {
            when (tvShow.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    tvShowAdapter.submitList(tvShow.data)
                    tvShowAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoading(status: Boolean){
        fragmentTvShowBinding.tvShowProgressBar.isVisible = status
    }
}