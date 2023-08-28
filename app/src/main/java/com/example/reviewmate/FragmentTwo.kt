package com.example.reviewmate

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewmate.common.Movie
import com.example.reviewmate.common.MoviesRepository
import com.example.reviewmate.databinding.FragmentOneBinding
import com.example.reviewmate.databinding.FragmentTwoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTwo.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTwo : Fragment() {
    lateinit var root : View

    var searchKeyword = ""

    private lateinit var searchMovies: RecyclerView
    private lateinit var searchMoviesAdapter: MovieAdapter
    private lateinit var searchMoviesLayoutMgr: LinearLayoutManager
    private var searchMoviesPage = 1
    lateinit var binding: FragmentTwoBinding


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle? ): View? {

        val binding = FragmentTwoBinding.inflate(inflater, container, false)

        searchMovies = binding.searchMovies
        searchMoviesLayoutMgr = GridLayoutManager(
            context,
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
                Toast.makeText(activity, "input keyword", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, searchKeyword, Toast.LENGTH_SHORT).show()
                getSearchMovies()

            }
            removeData()
        }

        return binding.root
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
        Toast.makeText(activity, "error error", Toast.LENGTH_SHORT).show()
    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
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
