package com.example.artspaceapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.artspaceapp.R

data class Art (
    @DrawableRes val imageResourceId: Int,
    @StringRes val artTitle: Int,
    @StringRes val artArtist: Int , @StringRes val artYear: Int)
val Arts = listOf(
    Art(R.drawable.monalisa, R.string.artworkTitle_1, R.string.artworkArtist_1, R.string.artworkYear_1),
    Art(R.drawable.creationofadam, R.string.artworkTitle_2, R.string.artworkArtist_2, R.string.artworkYear_2),
    Art(R.drawable.thegreatwave, R.string.artworkTitle_3, R.string.artworkArtist_3, R.string.artworkYear_3)
);