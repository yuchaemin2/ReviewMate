package com.example.reviewmate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reviewmate.common.Movie
import com.example.reviewmate.databinding.FragmentOneBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
/*
@SerializedName("id") val movieId : Long,
@SerializedName("title") val movieTitle : String,
@SerializedName("overview") val movieOverview : String, // 설명
@SerializedName("poster_path") val moviePoster: String,
@SerializedName("backdrop_path") val movieBackdrop: String, // 배경 이미지
@SerializedName("vote_average") val movieRate: Float, // 평점
@SerializedName("release_date") val movieDate: String, //
@SerializedName("popularity") val movieView: Float //조회수

 */
class FragmentOne : Fragment() {
    lateinit var binding : FragmentOneBinding
    private val itemList = listOf<Movie>(
        // 데이터 api사용!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Movie("123", "영화1", "")
        Movie(
            1,
            "영화1",
            "설명",
            "poster_path_string", // drawable 리소스의 이름을 사용하면 안됨
            "backdrop_path_string", // drawable 리소스의 이름을 사용하면 안됨
            6.9f,
            "2023-08-01",
            435.0f // float일 시 f붙여야 함
        ),
        Movie(
            2,
            "영화2",
            "설명",
            "poster_path_string", // drawable 리소스의 이름을 사용하면 안됨
            "backdrop_path_string", // drawable 리소스의 이름을 사용하면 안됨
            6.9f,
            "2023-08-02",
            435.0f // float일 시 f붙여야 함
        ),
        Movie(
            3,
            "영화3",
            "설명",
            "poster_path_string", // drawable 리소스의 이름을 사용하면 안됨
            "backdrop_path_string", // drawable 리소스의 이름을 사용하면 안됨
            6.9f,
            "2023-08-03",
            435.0f // float일 시 f붙여야 함
        ),
        Movie(
            4,
            "영화4",
            "설명",
            "poster_path_string", // drawable 리소스의 이름을 사용하면 안됨
            "backdrop_path_string", // drawable 리소스의 이름을 사용하면 안됨
            6.9f,
            "2023-08-04",
            435.0f // float일 시 f붙여야 함
        )
    )


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

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.popularMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.popularMovies.adapter = MovieAdapter(itemList)
        binding.recentMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recentMovies.adapter = MovieAdapter(itemList)

    }

}