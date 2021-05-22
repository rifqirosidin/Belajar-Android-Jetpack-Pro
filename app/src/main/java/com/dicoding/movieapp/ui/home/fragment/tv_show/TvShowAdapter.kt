package com.dicoding.movieapp.ui.home.fragment.tv_show

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

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>(){

    private var tvShowList = ArrayList<DataModel>()

    fun setTvShows(tvShow: List<DataModel>?){
        if (tvShow == null) return
        this.tvShowList.clear()
        this.tvShowList.addAll(tvShow)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemTvShowBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemTvShowBinding)
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShowList[position]
        holder.bind(tvShow)
    }

    class TvShowViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: DataModel){
            with(binding){
                tvTitle.text = tvShow.name
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, tvShow.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DataType.TV_SHOW.name)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                        .load(tvShow.poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_error)
                        .into(ivPoster)

            }
        }
    }
}


