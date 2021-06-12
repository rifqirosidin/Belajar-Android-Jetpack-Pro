package com.dicoding.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModel (
        var id: Int = 0,
        var name: String? = null,
        var description: String? = null,
        var poster: String? = null,
        var imgPreview: String? = null
) : Parcelable