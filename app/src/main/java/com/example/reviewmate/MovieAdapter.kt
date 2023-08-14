package com.example.reviewmate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.reviewmate.common.Movie
import com.example.reviewmate.databinding.ItemMovieBinding

class MovieAdapter (var movies : MutableList<Movie>, var onMovieClick:(movie:Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)
        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.moviePoster}")
                .transform(CenterCrop())
                .into(poster)
            binding.itemMovieTitle.text = movie.movieTitle
            itemView.setOnClickListener { onMovieClick.invoke(movie) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false // 직접 사용하면 안되기 때문
        ))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun appendMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size - 1
        )
    }
}