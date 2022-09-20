package com.example.splashscreen.domain.models

data class Item(
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet
)