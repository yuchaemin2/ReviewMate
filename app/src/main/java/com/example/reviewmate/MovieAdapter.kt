package com.example.reviewmate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewmate.common.Movie
import com.example.reviewmate.databinding.ItemMovieBinding

class MovieAdapter (var movies : List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false // 직접 사용하면 안되기 때문
            )
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class MovieViewHolder(val binding : ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie : Movie) {
            // 데이터 대입하는 코드

            binding.itemMovieTitle.text = movie.movieTitle

        }

    }
}