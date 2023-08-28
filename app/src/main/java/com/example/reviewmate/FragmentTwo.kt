package com.example.reviewmate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.reviewmate.common.Movie
import com.example.reviewmate.common.MoviesRepository
import com.example.reviewmate.common.MoviesRepository.Companion.getSearchMovies
import com.example.reviewmate.databinding.FragmentOneBinding
import com.example.reviewmate.databinding.FragmentTwoBinding
import com.google.android.material.color.utilities.MaterialDynamicColors.onError

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
    lateinit var binding: FragmentTwoBinding

    var searchKeyword = ""

    private lateinit var searchMovies: RecyclerView
    private lateinit var searchMoviesAdapter: MovieAdapter
    private lateinit var searchMoviesLayoutMgr: LinearLayoutManager
    private var searchMoviesPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTwoBinding.inflate(inflater, container, false)

        searchMovies = binding.searchMovies
        searchMoviesLayoutMgr = GridLayoutManager(
            context,
            3,
            RecyclerView.VERTICAL,
            false
        )
        searchMovies.layoutManager = searchMoviesLayoutMgr
        searchMoviesAdapter = MovieAdapter(mutableListOf()){ movie -> showMovieDetails(movie) }
        searchMovies.adapter = searchMoviesAdapter

        binding.eSearchWord.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE || (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                binding.bSearch.performClick() // bSearch 버튼 클릭
                handled = true
            }
            handled
        }

        binding.bSearch.setOnClickListener {
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

    private fun onSearchMoviesFetched(movies: List<Movie>) {
        searchMoviesAdapter.appendMovies(movies)
    }

    private fun onError() {
        Toast.makeText(activity, "error Movies", Toast.LENGTH_SHORT).show()
    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(MainActivity.MOVIE_BACKDROP, movie.movieBackdrop)
        intent.putExtra(MainActivity.MOVIE_POSTER, movie.moviePoster)
        intent.putExtra(MainActivity.MOVIE_TITLE, movie.movieTitle)
        intent.putExtra(MainActivity.MOVIE_RATING, movie.movieRate)
        intent.putExtra(MainActivity.MOVIE_RELEASE_DATE, movie.movieDate)
        intent.putExtra(MainActivity.MOVIE_OVERVIEW, movie.movieOverview)
        intent.putExtra(MainActivity.MOVIE_ID, movie.movieId)
        startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentTwo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}