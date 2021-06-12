package com.dicoding.movieapp.ui.home.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.movieapp.R
import com.dicoding.movieapp.model.DataModel
import com.dicoding.movieapp.utils.DataType.*
import com.dicoding.movieapp.utils.NetworkInfo.IMAGE_URL
import com.dicoding.movieapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)
        if (type.equals(MOVIE.name)){
            viewModel.getMovieDetail(id).observe(this, Observer {
                displayView(it)
            })
        } else if (type.equals(TV_SHOW.name)){
            viewModel.getTvShowDetail(id).observe(this, Observer {
                displayView(it)
            })
        }
    }

    private fun displayView(dataModel: DataModel){
        tv_title_detail_movie.text = dataModel.name
        tv_desc_detail_movie.text = dataModel.description
        Glide.with(this)
            .load(IMAGE_URL + dataModel.imgPreview)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(iv_detail_movie)
    }
}