package com.example.reviewmate

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewmate.common.Movie
import com.example.reviewmate.common.MoviesRepository
import com.example.reviewmate.common.TMDBClient
import com.example.reviewmate.databinding.FragmentListBinding
import com.example.reviewmate.databinding.FragmentOneBinding
import com.google.firebase.firestore.Query

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var ListMoviesRecyclerView: RecyclerView
    private lateinit var ListMoviesAdapter: MovieAdapter
    private lateinit var ListMoviesLayoutManager: LinearLayoutManager
//
//    private lateinit var topRatedMovies: RecyclerView
//    private lateinit var topRatedMoviesAdapter: MovieAdapter
//    private lateinit var topRatedMoviesLayoutMgr: LinearLayoutManager
//
//    private lateinit var upcomingMovies: RecyclerView
//    private lateinit var upcomingMoviesAdapter: MovieAdapter
//    private lateinit var upcomingMoviesLayoutMgr: LinearLayoutManager

    private lateinit var binding : FragmentListBinding
     var page : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        binding.chatListToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        setToolbar()

        // recyclerView Setting
        ListMoviesRecyclerView = binding.root.findViewById(R.id.feedRecyclerView)
        ListMoviesLayoutManager = GridLayoutManager(
            context,
            3, // 열의 개수
            RecyclerView.VERTICAL, // 아이템의 배치 방향 (수직)
            false // 리사이클러뷰 크기가 변하지 않음
        )
        ListMoviesRecyclerView.layoutManager = ListMoviesLayoutManager
        ListMoviesAdapter = MovieAdapter(mutableListOf()){ movie -> showMovieDetails(movie) }
        ListMoviesRecyclerView.adapter = ListMoviesAdapter

        val message = arguments?.getInt("message")
        when(message){
            1 -> {
                getPopularMovies()
                binding.movieDirectory.text = "가장 인기있는 영화"
            }
            2 -> {
                getTopRatedMovies()
                binding.movieDirectory.text = "Top Rated 평점"
            }
            3 -> {
                getUpcomingMovies()
                binding.movieDirectory.text = "Upcoming"
            }
        }
        getPopularMovies()
        return binding.root

    }
    private fun setToolbar() {
        binding.chatListToolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.popular -> {
                    (activity as MainActivity).loadFragment(ListFragment(), 1)
                    binding.movieDirectory.text = "가장 인기있는 영화"
                    return@setOnMenuItemClickListener true
                }
                R.id.toprated -> {
                    (activity as MainActivity).loadFragment(ListFragment(), 2)
//                    getTopRatedMovies()
                    binding.movieDirectory.text = "Top Rated 평점"
                    return@setOnMenuItemClickListener true
                }
                R.id.upcoming -> {
                    (activity as MainActivity).loadFragment(ListFragment(), 3)
                    binding.movieDirectory.text = "Upcoming"
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener true
            }
        }
    }

    private fun MoviesFetched(movies: List<Movie>) {
        ListMoviesAdapter.appendMovies(movies)
        // 스클롤로
        attachListMoviesOnScrollListener()/////////////////////////////////////////
    }

    // scroll
    private fun attachListMoviesOnScrollListener() {
        ListMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = ListMoviesLayoutManager.itemCount
                val visibleItemCount = ListMoviesLayoutManager.childCount
                val firstVisibleItem = ListMoviesLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    ListMoviesRecyclerView.removeOnScrollListener(this)
                    page++
                    getPopularMovies()
                }
            }
        })
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

    private fun getPopularMovies() {
        MoviesRepository.getPopularMovies(
            page,
            ::MoviesFetched,
            ::onError
        )

    }

    private fun getTopRatedMovies() {
        MoviesRepository.getTopRatedMovies(
            page,
            ::MoviesFetched,
            ::onError
        )
    }

    private fun getUpcomingMovies() {
        MoviesRepository.getUpcomingMovies(
            page,
            ::MoviesFetched,
            ::onError
        )
    }


    private fun onError() {
        Toast.makeText(activity, "error Movies", Toast.LENGTH_SHORT).show()
    }

}