package com.example.reviewmate

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewmate.common.Movie
import com.example.reviewmate.common.MoviesRepository
import com.example.reviewmate.common.MoviesRepository.Companion.getSearchMovies
import com.example.reviewmate.databinding.ActivityAddBinding
import com.example.reviewmate.databinding.ActivitySearchBinding
import com.example.reviewmate.databinding.FragmentTwoBinding

class SearchActivity : AppCompatActivity() {

    lateinit var root : View

    var searchKeyword = ""

    private lateinit var searchMovies: RecyclerView
    private lateinit var searchMoviesAdapter: MovieAdapter
    private lateinit var searchMoviesLayoutMgr: LinearLayoutManager
    private var searchMoviesPage = 1
    lateinit var binding: ActivitySearchBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchMovies = binding.searchMovies
        searchMoviesLayoutMgr = GridLayoutManager(
            baseContext,
            3, // 열의 개수
            RecyclerView.VERTICAL, // 아이템의 배치 방향 (수직)
            false // 리사이클러뷰 크기가 변하지 않음
        )



        searchMovies.layoutManager = searchMoviesLayoutMgr
        searchMoviesAdapter = MovieAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        searchMovies.adapter = searchMoviesAdapter


        binding.bSearch.setOnClickListener() {
            searchKeyword = binding.eSearchWord.text.toString()
            if(searchKeyword == "") {
                Toast.makeText(this, "input keyword", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, searchKeyword, Toast.LENGTH_SHORT).show()
                getSearchMovies()

            }

            removeData()
        }

        var toolbar = binding.toolbarBack
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayShowTitleEnabled(false)//타이틀 없애기

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

    private fun removeData() {
        searchMovies.removeAllViews()
        searchMoviesAdapter.removeMovies(searchMoviesAdapter.movies)
        searchMoviesAdapter.notifyDataSetChanged()

    }


    private fun getSearchMovies() {
        MoviesRepository.getSearchMovies(
            searchMoviesPage,
            searchKeyword,
            ::onSearchMoviesFetched,
            ::onError
        )
    }

    private fun attachSearchMoviesOnScrollListener() {
        searchMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = searchMoviesLayoutMgr.itemCount
                val visibleItemCount = searchMoviesLayoutMgr.childCount
                val firstVisibleItem = searchMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    searchMovies.removeOnScrollListener(this)
                    searchMoviesPage++
                    getSearchMovies()
                }
            }
        })
    }

    private fun onSearchMoviesFetched(movies: List<Movie>) {
        searchMoviesAdapter.appendMovies(movies)
        attachSearchMoviesOnScrollListener()
    }


    private fun onError() {
        Toast.makeText(baseContext, "error error", Toast.LENGTH_SHORT).show()
    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(baseContext, MovieDetailsActivity::class.java)
        intent.putExtra(MainActivity.MOVIE_ID, movie.movieId)
        intent.putExtra(MainActivity.MOVIE_POSTER, movie.moviePoster)
        intent.putExtra(MainActivity.MOVIE_TITLE, movie.movieTitle)
        intent.putExtra(MainActivity.MOVIE_RATING, movie.movieRate)
        intent.putExtra(MainActivity.MOVIE_OVERVIEW, movie.movieOverview)
        startActivity(intent)
    }

    // 패딩설정 나중에!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount

                if (position < spanCount) {
                    outRect.top = spacing
                }
                outRect.bottom = spacing
            } else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount

                if (position >= spanCount) {
                    outRect.top = spacing
                }
            }
        }
    }


}