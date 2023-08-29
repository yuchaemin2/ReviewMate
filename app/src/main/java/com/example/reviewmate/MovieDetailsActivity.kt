package com.example.reviewmate

import android.R.menu
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.reviewmate.MainActivity.Companion.MOVIE_BACKDROP
import com.example.reviewmate.MainActivity.Companion.MOVIE_ID
import com.example.reviewmate.MainActivity.Companion.MOVIE_OVERVIEW
import com.example.reviewmate.MainActivity.Companion.MOVIE_POSTER
import com.example.reviewmate.MainActivity.Companion.MOVIE_RATING
import com.example.reviewmate.MainActivity.Companion.MOVIE_RELEASE_DATE
import com.example.reviewmate.MainActivity.Companion.MOVIE_TITLE
import com.example.reviewmate.databinding.MovieDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.Query


class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: MovieDetailBinding
    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var overview: TextView
    private lateinit var releaseDate: TextView
    private lateinit var id: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backdrop = binding.movieBackdrop
        poster = binding.moviePoster
        title = binding.movieTitle
        rating = binding.movieRate
        overview = binding.movieOverview
        releaseDate = binding.movieReleaseDate
        id = binding.movieId

        val extras = intent.extras
        if (extras != null) {
            populateDetails(extras)
        } else {
            finish()
        }


        binding.addReviewBtn.setOnClickListener {
            if(MyApplication.checkAuth()){
                val intent = Intent(this, AddActivity::class.java)
                if (extras != null) {
                    intent.putExtra(MOVIE_TITLE, title.text.toString())
                    intent.putExtra(MOVIE_POSTER, extras.getString(MOVIE_POSTER))
                    intent.putExtra(MOVIE_ID, id.text.toString())
//                    intent.putExtra(MOVIE_ID, extras.getString(MOVIE_ID))
                }
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "인증을 진행해 주세요", Toast.LENGTH_SHORT).show()
            }
        }

        var toolbar = binding.toolbarBack
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayShowTitleEnabled(false)//타이틀 없애기
    }

    override fun onStart() {
        super.onStart()
        if(MyApplication.checkAuth()){
            MyApplication.db.collection("reviews")
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->
                    val itemList = mutableListOf<ItemFeedModel>()
                    for(document in result){
                        val item = document.toObject(ItemFeedModel::class.java)
                        // movieId로 바꾸기
                        if(id.text.equals(item.movieId)){
                            item.docId = document.id
                            itemList.add(item)
                        }
                        Log.d("ToyProject", "영화 아이디: ${id.text}")
                    }
                    binding.movieDetailRecyclerView.layoutManager = LinearLayoutManager(this)
                    binding.movieDetailRecyclerView.adapter = MyFeedAdapter(this, itemList)
                }
                .addOnFailureListener{
                    Toast.makeText(this, "데이터 획득 실패", Toast.LENGTH_SHORT).show()
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_back, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { //뒤로 가기 버튼
                onBackPressed() // 기본 뒤로가기 동작 수행
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun likeMovies() {
        binding.moviePoster.setOnClickListener{

        }
    }

    private fun populateDetails(extras: Bundle) {
        extras.getString(MOVIE_BACKDROP)?.let { backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280${backdropPath}")
                .transform(CenterCrop())
                .into(backdrop)
        }

        extras.getString(MOVIE_POSTER)?.let { posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342${posterPath}")
                .transform(CenterCrop())
                .into(poster)
        }

        title.text = extras.getString(MOVIE_TITLE, "")
        rating.rating = extras.getFloat(MOVIE_RATING, 0f) / 2
        overview.text = extras.getString(MOVIE_OVERVIEW, "")
        releaseDate.text = extras.getString(MOVIE_RELEASE_DATE, "")
        id.text = extras.getString(MOVIE_ID, "")
    }
}
