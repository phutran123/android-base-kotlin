package com.example.aspenbase.data

import android.os.*
import kotlinx.parcelize.*

@Parcelize
data class CardItem(
    val imageRes: Int, val title: String, val rating: Double, var isFavorite: Boolean = false
) : Parcelable
