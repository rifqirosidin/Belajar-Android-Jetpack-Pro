package com.dicoding.movieapp.ui.home.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.movieapp.R
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.databinding.ItemMovieBinding
import com.dicoding.movieapp.ui.home.detail.DetailActivity
import com.dicoding.movieapp.utils.DataType
import com.dicoding.movieapp.utils.NetworkInfo

class FavoriteMovieAdapter : PagedListAdapter<MovieEntity, FavoriteMovieAdapter.MovieViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    inner class MovieViewHolder (private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: MovieEntity){
            with(binding){
                tvTitle.text = movie.title
                Glide.with(itemView.context)
                    .load(NetworkInfo.IMAGE_URL + movie.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(ivPoster)
            }

            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, movie.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, DataType.MOVIE.name)
                itemView.context.startActivity(intent)
            }
        }
    }
}


