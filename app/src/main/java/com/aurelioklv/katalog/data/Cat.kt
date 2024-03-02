package com.aurelioklv.katalog.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val name: String,
    val imageUrl: String,
    val description: String,
    val origin: String,
    val temperament: String,
    val lifeSpan: String,
) : Parcelable
