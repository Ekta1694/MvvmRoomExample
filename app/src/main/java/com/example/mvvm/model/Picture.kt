package com.example.mvvm.model

import java.io.Serializable

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
): Serializable
