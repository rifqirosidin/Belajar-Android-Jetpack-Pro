package com.dicoding.movieapp.ui.home.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.movieapp.R
import com.dicoding.movieapp.model.DataModel
import com.dicoding.movieapp.utils.DataType.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var result: DataModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val id = intent.getStringExtra(EXTRA_DATA)
        val type = intent.getStringExtra(EXTRA_TYPE)
        if (type.equals(MOVIE.name)){
            supportActionBar?.title = getString(R.string.movie_detail)
            id?.let {
                viewModel.setMovieId(it)
            }
            result = viewModel.getMovieById()
        } else if (type.equals(TV_SHOW.name)){
            supportActionBar?.title = getString(R.string.tv_show_detail)
            id?.let {
                viewModel.setTvShowId(it)
            }
            result = viewModel.getTvShowById()
        }

        tv_title_detail_movie.text = result.name
        tv_desc_detail_movie.text = result.desc
        Glide.with(this)
                .load(result.img_preview)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(iv_detail_movie)

    }
}