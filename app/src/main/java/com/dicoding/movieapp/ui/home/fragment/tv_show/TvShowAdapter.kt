package com.dicoding.movieapp.ui.home.fragment.tv_show

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.movieapp.R
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.databinding.ItemMovieBinding
import com.dicoding.movieapp.ui.home.detail.DetailActivity
import com.dicoding.movieapp.utils.DataType
import com.dicoding.movieapp.utils.NetworkInfo.IMAGE_URL

class TvShowAdapter : PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemTvShowBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    class TvShowViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity){
            with(binding){
                tvTitle.text = tvShow.title
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, tvShow.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DataType.TV_SHOW.name)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(IMAGE_URL + tvShow.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_error)
                        .into(ivPoster)
            }
        }
    }
}


