package com.example.mvvm.model

import java.io.Serializable

data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
): Serializable