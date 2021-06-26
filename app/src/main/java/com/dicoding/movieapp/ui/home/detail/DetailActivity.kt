package com.dicoding.movieapp.ui.home.detail

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.dicoding.movieapp.R
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.databinding.ActivityDetailBinding
import com.dicoding.movieapp.utils.DataType.*
import com.dicoding.movieapp.utils.NetworkInfo.IMAGE_URL
import com.dicoding.movieapp.utils.Status
import com.dicoding.movieapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var dataCategory: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        dataCategory = intent.getStringExtra(EXTRA_TYPE)

        if (id != 0 && dataCategory != null){
            viewModel.setCatalog(id, dataCategory.toString())
            setupState()
            if (dataCategory == MOVIE.name){
                viewModel.getDetailMovie().observe(this, Observer { detail ->
                    when (detail.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            if (detail.data != null) {
                                showLoading(false)
                                Log.d("movie_data", detail.data.toString())
                                populateDataDetail(detail.data)

                            }
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            } else if (dataCategory == TV_SHOW.name){
                viewModel.getDetailTvShow().observe(this, Observer {detail ->
                    when (detail.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            if (detail.data != null) {
                                showLoading(false)
                                populateDataDetail(detail.data)
                            }
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }


        detailBinding.fabAddFavorite.setOnClickListener {
            onFabClicked()
        }
    }

    private fun onFabClicked() {
        Log.d("favorite_clicled", dataCategory.toString())
        if (dataCategory == MOVIE.name) {
            Log.d("favorite_clicled", "movie")
            viewModel.setFavoriteMovie()
        } else if (dataCategory == TV_SHOW.name) {
            viewModel.setFavoriteTvShow()
        }
    }

    private fun showLoading(status: Boolean){
        detailBinding.progressBarDetail.isVisible = status
    }

    private fun setupState() {
        if (dataCategory == MOVIE.name) {
            viewModel.getDetailMovie().observe(this, Observer{ movie ->
                when (movie.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        if (movie.data != null) {
                            showLoading(false)
                            val state = movie.data.isFavorite
                            Log.d("favorited", state.toString())
                            setFavoriteState(state)
                            populateDataDetail(movie.data)
                        }
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (dataCategory == TV_SHOW.name) {
            viewModel.getDetailTvShow().observe(this, Observer{ tvShow ->
                when (tvShow.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            showLoading(false)
                            val state = tvShow.data.isFavorite
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun setFavoriteState(state: Boolean) {
        val fab = detailBinding.fabAddFavorite
        if (state) {
            fab.setImageResource(R.drawable.ic_favorited_red)
            fab.setBackgroundColor(Color.RED)
        } else {
            fab.setImageResource(R.drawable.ic_favorited_white)
        }
    }

    @JvmName("populateDataDetailForMovie")
    private fun populateDataDetail(movie: MovieEntity) {
        with(movie) {
            detailBinding.tvTitleDetailMovie.text = title
            detailBinding.tvDescDetailMovie.text = overview
            Glide.with(this@DetailActivity)
                .asBitmap()
                .load(IMAGE_URL + posterPath)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        detailBinding.ivDetailMovie.setImageBitmap(resource)
//                        setColorByPalette(resource)
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {}
                })

            Glide.with(this@DetailActivity)
                .load(IMAGE_URL + backdropPath)
                .into(detailBinding.ivDetailMovie)

            detailBinding.ivDetailMovie.tag = posterPath
//            detailBinding.ivDetailMovie.tag = this.backdropPath
            showLoading(false)
        }
    }

    @JvmName("populateDataDetailForTvShow")
    private fun populateDataDetail(tvShow: TvShowEntity) {
        with(tvShow) {

            detailBinding.tvTitleDetailMovie.text = this.title
            detailBinding.tvDescDetailMovie.text = this.overview

            Glide.with(this@DetailActivity)
                .asBitmap()
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .load(IMAGE_URL + this.posterPath)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        detailBinding.ivDetailMovie.setImageBitmap(resource)
//                        setColorByPalette(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })

            Glide.with(this@DetailActivity)
                .load(IMAGE_URL + this.backdropPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(detailBinding.ivDetailMovie)

            detailBinding.ivDetailMovie.tag = this.posterPath
//            detailBinding.ivDetailMovie.tag = this.backdropPath

            showLoading(false)
        }
    }
}