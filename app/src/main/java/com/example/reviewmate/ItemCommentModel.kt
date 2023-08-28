package com.example.reviewmate

data class ItemCommentModel(
    var docId: String? = null,
    var user: String? = null,
    var text: String? = null,
    var reviewId: String? = null,
    var time: String? = null,
    var img: String? = null
)

