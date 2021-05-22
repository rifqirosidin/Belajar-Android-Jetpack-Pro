package com.dicoding.movieapp.ui.home.fragment.tv_show

import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.model.DataModel
import com.dicoding.movieapp.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShows() : List<DataModel> = DataDummy.createDataTvShowDummy()
}