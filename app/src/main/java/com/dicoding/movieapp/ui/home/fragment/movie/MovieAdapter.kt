package com.dicoding.movieapp.ui.home.fragment.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.movieapp.R
import com.dicoding.movieapp.databinding.ItemMovieBinding
import com.dicoding.movieapp.model.DataModel
import com.dicoding.movieapp.ui.home.detail.DetailActivity
import com.dicoding.movieapp.utils.DataType
import com.dicoding.movieapp.utils.NetworkInfo.IMAGE_URL

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movieList = ArrayList<DataModel>()

    fun setMovie(model: List<DataModel>){
        if (model == null) return
        this.movieList.clear()
        this.movieList.addAll(model)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: DataModel) {
            with(binding){
                tvTitle.text = model.name
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, model.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DataType.MOVIE.name)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(IMAGE_URL + model.poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_error)
                        .into(ivPoster)
            }
        }

    }
}


