package com.example.reviewmate

import android.media.Image
import android.widget.ImageView

data class UserModel(
    var strName : String? = null,
    var uid : String? = null,
    var userEmail : String? = null,
    var imageUrl : String? = null,
    var userLevel : String? = "3"
)