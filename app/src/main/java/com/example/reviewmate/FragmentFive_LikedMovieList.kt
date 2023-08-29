package com.example.reviewmate

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewmate.MyApplication.Companion.auth
import com.example.reviewmate.common.Movie
import com.example.reviewmate.common.MoviesRepository
import com.example.reviewmate.common.MoviesRepository.Companion.getLikedMovies
import com.example.reviewmate.common.MoviesRepository.Companion.getUpcomingMovies
import com.example.reviewmate.databinding.FragmentFiveLikedMovieListBinding
import com.google.android.material.color.utilities.MaterialDynamicColors.onError
import com.google.firebase.firestore.Query

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentFive_LikedMovieList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentFive_LikedMovieList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentFiveLikedMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiveLikedMovieListBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        binding.chatListToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.chatListToolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.menu_delete_all -> {
                    showDeleteConfirmationDialog()
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener true
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(MyApplication.checkAuth()){
            MyApplication.db.collection("liked_movies")
                .orderBy("movieId", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->
                    val itemList = mutableListOf<LikedMovieModel>()
                    for(document in result){
                        val item = document.toObject(LikedMovieModel::class.java)
                        if(item.userId == auth.uid){
                            item.docId = document.id
                            itemList.add(item)
                            if(result.size() == 0){
                                binding.textView.visibility = View.VISIBLE
                            }else{
                                binding.textView.visibility = View.INVISIBLE
                            }
                        }
                    }
                    binding.feedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding.feedRecyclerView.adapter = MovieAdapter(mutableListOf()){ movie -> showMovieDetails(movie) }
                    //goReviewDtaill()
                }
                .addOnFailureListener{
                    Toast.makeText(requireContext(), "데이터 획득 실패", Toast.LENGTH_SHORT).show()
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

    private fun deleteAllUserLikedMovie(userEmail: String) {
        val currentUser = MyApplication.auth.currentUser

        currentUser?.let {
            MyApplication.db.collection("liked_movies")
                .whereEqualTo("userId", auth.uid)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (documentSnapshot in querySnapshot.documents) {
                        val docId = documentSnapshot.id
                        MyApplication.db.collection("liked_movies").document(docId)
                            .delete()
                            .addOnSuccessListener {
                                Toast.makeText(requireContext(), "관심영화가 모두 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                                refreshRecyclerView()
                            }
                            .addOnFailureListener {  }
                        isRemoving
                    }
                }
                .addOnFailureListener {  }
        }
    }

    private fun refreshRecyclerView() {
        // 리사이클러뷰를 새로고침하고 데이터를 다시 로드
        val currentUser = auth.currentUser

        currentUser?.let {
            MyApplication.db.collection("liked_movies")
                .orderBy("movieId", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->
                    val itemList = mutableListOf<LikedMovieModel>()
                    for(document in result){
                        val item = document.toObject(LikedMovieModel::class.java)
                        if(auth.uid.equals(item.userId)){
                            item.docId = document.id
                            itemList.add(item)
                        }
                    }
                    // 기존 리사이클러뷰 어댑터에 새 데이터 설정
                    binding.feedRecyclerView.adapter = MovieAdapter(mutableListOf()){ movie -> showMovieDetails(movie) }
                    binding.textView.visibility = View.VISIBLE
                }
                .addOnFailureListener{
                    Toast.makeText(requireContext(), "데이터 획득 실패", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun showDeleteConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("정말로 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                deleteAllUserLikedMovie(MyApplication.email.toString())
            }
            .setNegativeButton("취소") { _, _ ->
                // "취소" 버튼 클릭 시, 아무 동작 없음
            }
            .create()
            .show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmemtFive_LikedMovieList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentFive_LikedMovieList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}