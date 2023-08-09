package com.example.reviewmate

import android.media.Image
import android.widget.ImageView

data class UserModel(
    var uid : String? = null,
    var userEmail : String? = null,
    var imageUrl : String? = null,
    var userLevel : Int = 1,
    var userReviewCount: Int = 0
)