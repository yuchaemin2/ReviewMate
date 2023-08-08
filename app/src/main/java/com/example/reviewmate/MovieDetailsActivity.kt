package com.example.reviewmate

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.reviewmate.MainActivity.Companion.MOVIE_OVERVIEW
import com.example.reviewmate.MainActivity.Companion.MOVIE_POSTER
import com.example.reviewmate.MainActivity.Companion.MOVIE_RATING
import com.example.reviewmate.MainActivity.Companion.MOVIE_TITLE
import com.example.reviewmate.databinding.FragmentFiveReviewListBinding
import com.google.firebase.firestore.Query

class MovieDetailsActivity : ComponentActivity() {

    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var overview: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)

            poster = findViewById(R.id.moviePoster)
            title = findViewById(R.id.movieTitle)
            rating = findViewById(R.id.movieRate)
            overview = findViewById(R.id.movieOverview)

            val extras = intent.extras

            if (extras != null) {
                populateDetails(extras)
            } else {
                finish()
            }
        }


        private fun populateDetails(extras: Bundle) {
            extras.getString(MOVIE_POSTER)?.let { posterPath ->
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w342$posterPath")
                    .transform(CenterCrop())
                    .into(poster)
            }

            title.text = extras.getString(MOVIE_TITLE, "")
            rating.rating = extras.getFloat(MOVIE_RATING, 0f) / 2
            overview.text = extras.getString(MOVIE_OVERVIEW, "")
        }


}

