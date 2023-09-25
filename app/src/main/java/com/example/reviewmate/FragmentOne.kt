package com.example.reviewmate

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewmate.MyApplication.Companion.auth
import com.example.reviewmate.common.Movie
import com.example.reviewmate.common.MoviesRepository
import com.example.reviewmate.databinding.FragmentOneBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.Query
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentOne : Fragment() {

    private var selectedDate: LocalDate? = null
    private var selectedDate_add1: String = "0"
    lateinit var binding: FragmentOneBinding

    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager

    private lateinit var topRatedMovies: RecyclerView
    private lateinit var topRatedMoviesAdapter: MovieAdapter
    private lateinit var topRatedMoviesLayoutMgr: LinearLayoutManager

    private lateinit var upcomingMovies: RecyclerView
    private lateinit var upcomingMoviesAdapter: MovieAdapter
    private lateinit var upcomingMoviesLayoutMgr: LinearLayoutManager

    private var reviewDates: HashSet<CalendarDay> = HashSet() // 리뷰 날짜를 저장할 HashSet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        if(MyApplication.checkAuth()){
            binding.HomeEmailView.text = "${MyApplication.email}님 환영합니다!"
            userLevelCount()
//            binding.reviewCount.text = MyApplication.
        } else {
            binding.HomeEmailView.text = "로그인 혹은 회원가입을 진행해주세요."
            binding.reviewCount.visibility = View.GONE
            binding.userLevel.visibility = View.GONE
        }
        fetchReviewDates()


        // CalendarView에 날짜 데코레이터 추가
        val eventDecorator = EventDecorator(Color.RED, reviewDates)
        binding.calendarView.addDecorator(eventDecorator)

        if (MyApplication.checkAuth()) {
            binding.HomeEmailView.text = "${MyApplication.email}님 환영합니다!"
        } else {
            binding.HomeEmailView.text = "로그인 혹은 회원가입을 진행해주세요."
        }

        val currentDate = LocalDate.now()
        val calendarDay =
            CalendarDay.from(currentDate.year, currentDate.monthValue, currentDate.dayOfMonth)
        binding.calendarView.setDateSelected(calendarDay, true)

        binding.calendarView.setOnDateChangedListener(object : OnDateSelectedListener {
            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean
            ) {
                selectedDate = LocalDate.of(
                    date.year,
                    date.month,
                    date.day
                )

                selectedDate_add1 = LocalDate.of(
                    date.year,
                    date.month,
                    date.day + 1
                ).toString()

                val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val formattedDate: String = selectedDate.toString().format(dateFormat)

                Toast.makeText(context, "선택한 날짜: $formattedDate", Toast.LENGTH_SHORT).show()

                updateReviewListForSelectedDate()
            }
        })

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
        popularMoviesAdapter = MovieAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        popularMovies.adapter = popularMoviesAdapter

        getPopularMovies()

        topRatedMovies = binding.topRatedMovies
        topRatedMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        topRatedMovies.layoutManager = topRatedMoviesLayoutMgr
        topRatedMoviesAdapter = MovieAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        topRatedMovies.adapter = topRatedMoviesAdapter

        getTopRatedMovies()

        upcomingMovies = binding.upcomingMovies
        upcomingMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        upcomingMovies.layoutManager = upcomingMoviesLayoutMgr
        upcomingMoviesAdapter = MovieAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        upcomingMovies.adapter = upcomingMoviesAdapter

        getUpcomingMovies()

        return binding.root
    }

    private fun fetchReviewDates() {
        // 파이어베이스에서 리뷰 날짜를 가져와 reviewDates에 추가
        MyApplication.db.collection("reviews")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    val item = document.toObject(ItemFeedModel::class.java)

                    val dateStr = item.date // 리뷰의 날짜를 가져옴, 날짜 형식에 따라 변환 필요
                    if (dateStr != null) {
                        val date = parseDate(dateStr) // 날짜 문자열을 CalendarDay 객체로 파싱
                        if (date != null) {
                            if(item.email == MyApplication.email) { // 내가 쓴 리뷰만
                                reviewDates.add(date) //
                            }

                            Log.d("days", "reviewDates : ${date}")
                            Toast.makeText(context, "${date}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                Log.d("days","all reviewEates : ${reviewDates.size}, booleaan: ${reviewDates.contains(
                    CalendarDay.from(2023,9,18))}")
                // CalendarView를 업데이트하여 도트를 표시
                binding.calendarView.invalidateDecorators()
            }
            .addOnFailureListener {
                onError()
            }
    }

    private fun parseDate(dateStr: String): CalendarDay? {
        try {
            val dateFormat = SimpleDateFormat("yyyy-M-dd", Locale.getDefault())
            val date = dateFormat.parse(dateStr)
            if (date != null) {
                val calendar = Calendar.getInstance()
                calendar.time = date
                return CalendarDay.from(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1, // 월은 0부터 시작하므로 1을 더합니다.
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }


    private fun updateReviewListForSelectedDate() {
        if (MyApplication.checkAuth()) {
            MyApplication.db.collection("reviews")
                .whereGreaterThanOrEqualTo("date", selectedDate.toString())
                .whereLessThan("date", selectedDate_add1)
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->
                    val itemList = mutableListOf<ItemFeedModel>()
                    for (document in result) {
                        val item = document.toObject(ItemFeedModel::class.java)
                        if (MyApplication.email.equals(item.email)) {
                            item.docId = document.id
                            itemList.add(item)
                        }
                    }
                    val bottomSheetDialog = BottomSheetDialog(requireContext())
                    val view = LayoutInflater.from(requireContext()).inflate(
                        R.layout.bottom_sheet_review_list,
                        null
                    )
                    val recyclerView = view.findViewById<RecyclerView>(R.id.bottomSheetRecyclerView)
                    val date = view.findViewById<TextView>(R.id.dateString)
                    val guide = view.findViewById<TextView>(R.id.textView)

                    date.text = selectedDate.toString()
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = MyFeedAdapter(requireContext(), itemList)

                    if (result.size() == 0 || result.size() < 0) guide.visibility = View.VISIBLE

                    bottomSheetDialog.setContentView(view)
                    bottomSheetDialog.show()
                }
                .addOnFailureListener {
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
    private fun userLevelCount(){
        if(MyApplication.checkAuth()){
            MyApplication.db.collection("users").document(auth.uid.toString())
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        binding.userLevel.text = documentSnapshot.getString("userLevel")
                        var reviewNumber = documentSnapshot.getLong("userReviewCount")
                        if (reviewNumber != null) {
                            if(reviewNumber < 10){
                                binding.reviewCount.text = "${10-reviewNumber}"
                            }
                            else{
                                reviewNumber = reviewNumber % 10
                                binding.reviewCount.text = "${10-reviewNumber}"
                            }
                        }
                    }
                }
                .addOnFailureListener { e ->
                    //
                }
        }
    }


}

class EventDecorator() : DayViewDecorator {


    private var color = Color.GREEN
    private var dates: HashSet<CalendarDay> =HashSet() // 날짜를 저장할 HashSet 초기화

    constructor(color: Int, reviewDates: HashSet<CalendarDay>) : this() {
        this.color = color
        this.dates = reviewDates
        Log.d("days", "dates list size : ${dates.size}")



    }


    override fun shouldDecorate(day: CalendarDay?): Boolean {
//        val today = CalendarDay.today()
//        return day?.equals(today) == true
        Log.d("days", "today : ${CalendarDay.today()}")
        return dates.contains(day)
    }


    override fun decorate(view: DayViewFacade?) {
//        //view?.addSpan(DotSpan(10F, color))
//        view?.setSelectionDrawable(drawable)
        view?.addSpan(DotSpan(5F, color)) // 원 모양의 도트 추가 (크기와 색상 지정 가능)
    }

}