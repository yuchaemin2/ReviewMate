package com.example.reviewmate

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewmate.MyApplication.Companion.auth
import com.example.reviewmate.common.Movie
import com.example.reviewmate.common.MoviesRepository
import com.example.reviewmate.databinding.FragmentOneBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentOne : Fragment() {

    private var selectedDate: String = "0"
    private var selectedDate_add1: String = "0"
    lateinit var binding : FragmentOneBinding

    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager

    private lateinit var topRatedMovies: RecyclerView
    private lateinit var topRatedMoviesAdapter: MovieAdapter
    private lateinit var topRatedMoviesLayoutMgr: LinearLayoutManager

    private lateinit var upcomingMovies: RecyclerView
    private lateinit var upcomingMoviesAdapter: MovieAdapter
    private lateinit var upcomingMoviesLayoutMgr: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOneBinding.inflate(inflater, container, false)

        if(MyApplication.checkAuth()){
            binding.HomeEmailView.text = "${MyApplication.email}님 환영합니다!"
        } else {
            binding.HomeEmailView.text = "로그인 혹은 회원가입을 진행해주세요."
        }

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance() // 일단 현재 날짝 가져옴
            calendar.set(year, month, dayOfMonth) // 사용자가 선택한 날짜로 Calendar 객체를 업데이트

            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            selectedDate = dateFormat.format(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            selectedDate_add1 = dateFormat.format(calendar.time) //

            Toast.makeText(context, "선택한 날짜: $selectedDate", Toast.LENGTH_SHORT).show()
            updateReviewListForSelectedDate()
            // 사용자가 선택한 날짜에 대한 처리를 수행하도록 코드를 추가하세요.
            // 예: updateReviewListForSelectedDate(formattedDate)
        }

        binding.menuSearch.setOnClickListener {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }
        binding.textView1.setOnClickListener {
            (activity as MainActivity).loadFragment(ListFragment(), 1)
        }
        binding.textView2.setOnClickListener {
            (activity as MainActivity).loadFragment(ListFragment(), 2)
        }
        binding.textView3.setOnClickListener {
            (activity as MainActivity).loadFragment(ListFragment(), 3)
        }

        popularMovies = binding.popularMovies
        popularMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularMovies.layoutManager = popularMoviesLayoutMgr
        popularMoviesAdapter = MovieAdapter(mutableListOf()){ movie -> showMovieDetails(movie) }
        popularMovies.adapter = popularMoviesAdapter

        getPopularMovies()

        topRatedMovies = binding.topRatedMovies
        topRatedMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        topRatedMovies.layoutManager = topRatedMoviesLayoutMgr
        topRatedMoviesAdapter = MovieAdapter(mutableListOf()){ movie -> showMovieDetails(movie) }
        topRatedMovies.adapter = topRatedMoviesAdapter

        getTopRatedMovies()

        upcomingMovies = binding.upcomingMovies
        upcomingMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        upcomingMovies.layoutManager = upcomingMoviesLayoutMgr
        upcomingMoviesAdapter = MovieAdapter(mutableListOf()){ movie -> showMovieDetails(movie) }
        upcomingMovies.adapter = upcomingMoviesAdapter

        getUpcomingMovies()

        return binding.root
    }

    private fun updateReviewListForSelectedDate() {
        if(MyApplication.checkAuth()){
            MyApplication.db.collection("reviews")
                .whereGreaterThanOrEqualTo("date", selectedDate)// 비효율적이잖아...........
                .whereLessThan("date", selectedDate_add1)
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->
                    val itemList = mutableListOf<ItemFeedModel>()
                    for(document in result){
                        val item = document.toObject(ItemFeedModel::class.java)
                        if(MyApplication.email.equals(item.email)){
                            item.docId = document.id
                            itemList.add(item)
                        }
                    }
                    // Create and show the bottom sheet with lecture list
                    val bottomSheetDialog = BottomSheetDialog(requireContext())
                    val view = LayoutInflater.from(requireContext()).inflate(
                        R.layout.bottom_sheet_review_list,
                        null
                    )
                    val recyclerView = view.findViewById<RecyclerView>(R.id.bottomSheetRecyclerView)
                    val date = view.findViewById<TextView>(R.id.dateString)
                    val guide = view.findViewById<TextView>(R.id.textView)

                    date.setText(selectedDate)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = MyFeedAdapter(requireContext(), itemList) // 사용자 선택한 날짜에 맞는 아이템 리스트로 설정

                    if(result.size() == 0 || result.size() < 0) guide.visibility = View.VISIBLE

                    bottomSheetDialog.setContentView(view)
                    bottomSheetDialog.show()
                }
                .addOnFailureListener{
                    onError()
                }
        }
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
            1,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun getTopRatedMovies() {
        MoviesRepository.getTopRatedMovies(
            1,
            ::onTopRatedMoviesFetched,
            ::onError
        )
    }

    private fun getUpcomingMovies() {
        MoviesRepository.getUpcomingMovies(
            1,
            ::onUpcomingMoviesFetched,
            ::onError
        )
    }


    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.appendMovies(movies)
    }

    private fun onTopRatedMoviesFetched(movies: List<Movie>) {
        topRatedMoviesAdapter.appendMovies(movies)
    }

    private fun onUpcomingMoviesFetched(movies: List<Movie>) {
        upcomingMoviesAdapter.appendMovies(movies)
    }

    private fun onError() {
        Toast.makeText(activity, "error Movies", Toast.LENGTH_SHORT).show()
    }

}