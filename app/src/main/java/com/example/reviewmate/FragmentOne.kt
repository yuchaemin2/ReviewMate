package com.example.reviewmate

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewmate.common.Movie
import com.example.reviewmate.common.MoviesRepository
import com.example.reviewmate.common.MoviesRepository.Companion.getTopRatedMovies
import com.example.reviewmate.common.MoviesRepository.Companion.getUpcomingMovies
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
        binding.btnGoSearch.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance() // 일단 현재 날짝 가져옴
            calendar.set(year, month, dayOfMonth) // 사용자가 선택한 날짜로 Calendar 객체를 업데이트

            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            selectedDate = dateFormat.format(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            selectedDate_add1 = dateFormat.format(calendar.time) //

            Toast.makeText(context, "선택한 날짜: $selectedDate", Toast.LENGTH_SHORT).show()
            showBottomSheet()
            // 사용자가 선택한 날짜에 대한 처리를 수행하도록 코드를 추가하세요.
            // 예: updateReviewListForSelectedDate(formattedDate)
        }

        popularMovies = binding.root.findViewById(R.id.popular_movies)
        popularMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularMovies.layoutManager = popularMoviesLayoutMgr
        popularMoviesAdapter = MovieAdapter(mutableListOf()){ movie -> showMovieDetails(movie) }
        popularMovies.adapter = popularMoviesAdapter

        getPopularMovies()

        topRatedMovies = binding.root.findViewById(R.id.top_rated_movies)
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

    private fun showBottomSheet() {
        // bottomseet style도 설정
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        val bottomSheetView = layoutInflater.inflate(R.layout.list_reviews, null)

        // 리사이클러뷰 초기화 및 어댑터 설정
        val recyclerView = bottomSheetView.findViewById<RecyclerView>(R.id.BottomSeetRecyclerView)
        if (MyApplication.checkAuth()) {
            MyApplication.db.collection("reviews")
                .whereGreaterThanOrEqualTo("date", selectedDate)// 비효율적이잖아...........
                .whereLessThan("date", selectedDate_add1)
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->
                    val itemList = mutableListOf<ItemFeedModel>()
                    for (document in result) {
                        val item = document.toObject(ItemFeedModel::class.java)
                        if(MyApplication.email.equals(item.email)){
                            item.docId = document.id
                            itemList.add(item)
                        }
                    }
                    val adapter = MyFeedAdapter(requireContext(), itemList, "FragmentOne")
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                    Log.d("ToyProject", "${itemList}")

                    //recyclerview 아이템 클릭 이벤트 처리


                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "데이터 획득 실패", Toast.LENGTH_SHORT).show()
                }

            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }
    }
//    private fun updateReviewListForSelectedDate() {
//
//        if(MyApplication.checkAuth()){
//            MyApplication.db.collection("reviews")
//                .whereGreaterThanOrEqualTo("date", selectedDate)// 비효율적이잖아...........
//                .whereLessThan("date", selectedDate_add1)
//                .orderBy("date", Query.Direction.DESCENDING)
//                .get()
//                .addOnSuccessListener { result ->
//                    val itemList = mutableListOf<ItemFeedModel>()
//                    for(document in result){
//                        val item = document.toObject(ItemFeedModel::class.java)
//                        if(MyApplication.email.equals(item.email)){
//                            item.docId = document.id
//                            itemList.add(item)
//                        }
//                    }
//                    binding.feedRecyclerView.layoutManager = LinearLayoutManager(
//                        context,
//                        LinearLayoutManager.HORIZONTAL,
//                        false
//                    )
//                    binding.feedRecyclerView.adapter = ItemFeedModel(requireContext(), itemList, "FragmentOne")
//                    Log.d("ToyProject", "${itemList}")
//                }
//                .addOnFailureListener{
//                    onError()
//                }
//        }
//    }


}