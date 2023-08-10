package com.example.reviewmate

import android.media.Image
import android.widget.ImageView

data class UserModel(
    var uid : String? = null,
    var userEmail : String? = null,
    var imageUrl : String? = null,
    var userLevel : String? = null,
    var userReviewCount: Int = 0
)