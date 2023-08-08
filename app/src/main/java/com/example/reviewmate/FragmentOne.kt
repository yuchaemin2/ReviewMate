package com.example.reviewmate

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
import com.example.reviewmate.common.MoviesRepository.Companion.getPopularMovies
import com.example.reviewmate.databinding.FragmentOneBinding
import com.google.android.material.color.utilities.MaterialDynamicColors.onError
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

    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager
    private var selectedDate: String = "0"
    private var selectedDate_add1: String = "0"
    lateinit var binding : FragmentOneBinding


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


            popularMovies = binding.root.findViewById(R.id.popular_movies)
            popularMoviesLayoutMgr = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            popularMovies.layoutManager = popularMoviesLayoutMgr
            popularMoviesAdapter = MovieAdapter(mutableListOf())
            popularMovies.adapter = popularMoviesAdapter

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
        getPopularMovies()

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
                    binding.feedRecyclerView.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    binding.feedRecyclerView.adapter = MyFeedAdapter(requireContext(), itemList)
                    Log.d("ToyProject", "${itemList}")
                }
                .addOnFailureListener{
                    onError()
                }
        }
    }

//    private fun onReviewsForDateFetched(reviews: List<Review>) {
//        // 리뷰 목록을 가져온 후 RecyclerView 어댑터를 업데이트합니다
//        // 예: recentMoviesAdapter.updateReviews(reviews)
//    }


    fun getPopularMovies(){
        MoviesRepository.getPopularMovies(
            1,
            ::onPopularMoviesFetched,
            ::onError
        )}
    fun calenderViewOnClickListener() {

    }


    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.appendMovies(movies)
    }

    private fun onError() {
        Toast.makeText(activity, "error Movies", Toast.LENGTH_SHORT).show()
    }

}