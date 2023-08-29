// Generated by view binder compiler. Do not edit!
package com.example.reviewmate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.reviewmate.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MovieDetailBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ExtendedFloatingActionButton addReviewBtn;

  @NonNull
  public final Guideline backdropGuideline;

  @NonNull
  public final ImageView movieBackdrop;

  @NonNull
  public final RecyclerView movieDetailRecyclerView;

  @NonNull
  public final TextView movieId;

  @NonNull
  public final TextView movieOverview;

  @NonNull
  public final ImageView moviePoster;

  @NonNull
  public final CardView moviePosterCard;

  @NonNull
  public final Barrier moviePosterTitleBarrier;

  @NonNull
  public final RatingBar movieRate;

  @NonNull
  public final TextView movieReleaseDate;

  @NonNull
  public final Barrier movieReviewBarrier;

  @NonNull
  public final TextView movieTitle;

  @NonNull
  public final NestedScrollView nestedScrollView;

  @NonNull
  public final Toolbar toolbarBack;

  private MovieDetailBinding(@NonNull RelativeLayout rootView,
      @NonNull ExtendedFloatingActionButton addReviewBtn, @NonNull Guideline backdropGuideline,
      @NonNull ImageView movieBackdrop, @NonNull RecyclerView movieDetailRecyclerView,
      @NonNull TextView movieId, @NonNull TextView movieOverview, @NonNull ImageView moviePoster,
      @NonNull CardView moviePosterCard, @NonNull Barrier moviePosterTitleBarrier,
      @NonNull RatingBar movieRate, @NonNull TextView movieReleaseDate,
      @NonNull Barrier movieReviewBarrier, @NonNull TextView movieTitle,
      @NonNull NestedScrollView nestedScrollView, @NonNull Toolbar toolbarBack) {
    this.rootView = rootView;
    this.addReviewBtn = addReviewBtn;
    this.backdropGuideline = backdropGuideline;
    this.movieBackdrop = movieBackdrop;
    this.movieDetailRecyclerView = movieDetailRecyclerView;
    this.movieId = movieId;
    this.movieOverview = movieOverview;
    this.moviePoster = moviePoster;
    this.moviePosterCard = moviePosterCard;
    this.moviePosterTitleBarrier = moviePosterTitleBarrier;
    this.movieRate = movieRate;
    this.movieReleaseDate = movieReleaseDate;
    this.movieReviewBarrier = movieReviewBarrier;
    this.movieTitle = movieTitle;
    this.nestedScrollView = nestedScrollView;
    this.toolbarBack = toolbarBack;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MovieDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MovieDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.movie_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MovieDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_review_btn;
      ExtendedFloatingActionButton addReviewBtn = ViewBindings.findChildViewById(rootView, id);
      if (addReviewBtn == null) {
        break missingId;
      }

      id = R.id.backdrop_guideline;
      Guideline backdropGuideline = ViewBindings.findChildViewById(rootView, id);
      if (backdropGuideline == null) {
        break missingId;
      }

      id = R.id.movie_backdrop;
      ImageView movieBackdrop = ViewBindings.findChildViewById(rootView, id);
      if (movieBackdrop == null) {
        break missingId;
      }

      id = R.id.movieDetailRecyclerView;
      RecyclerView movieDetailRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (movieDetailRecyclerView == null) {
        break missingId;
      }

      id = R.id.movieId;
      TextView movieId = ViewBindings.findChildViewById(rootView, id);
      if (movieId == null) {
        break missingId;
      }

      id = R.id.movie_overview;
      TextView movieOverview = ViewBindings.findChildViewById(rootView, id);
      if (movieOverview == null) {
        break missingId;
      }

      id = R.id.movie_poster;
      ImageView moviePoster = ViewBindings.findChildViewById(rootView, id);
      if (moviePoster == null) {
        break missingId;
      }

      id = R.id.movie_poster_card;
      CardView moviePosterCard = ViewBindings.findChildViewById(rootView, id);
      if (moviePosterCard == null) {
        break missingId;
      }

      id = R.id.movie_poster_title_barrier;
      Barrier moviePosterTitleBarrier = ViewBindings.findChildViewById(rootView, id);
      if (moviePosterTitleBarrier == null) {
        break missingId;
      }

      id = R.id.movie_rate;
      RatingBar movieRate = ViewBindings.findChildViewById(rootView, id);
      if (movieRate == null) {
        break missingId;
      }

      id = R.id.movie_release_date;
      TextView movieReleaseDate = ViewBindings.findChildViewById(rootView, id);
      if (movieReleaseDate == null) {
        break missingId;
      }

      id = R.id.movie_review_barrier;
      Barrier movieReviewBarrier = ViewBindings.findChildViewById(rootView, id);
      if (movieReviewBarrier == null) {
        break missingId;
      }

      id = R.id.movie_title;
      TextView movieTitle = ViewBindings.findChildViewById(rootView, id);
      if (movieTitle == null) {
        break missingId;
      }

      id = R.id.nestedScrollView;
      NestedScrollView nestedScrollView = ViewBindings.findChildViewById(rootView, id);
      if (nestedScrollView == null) {
        break missingId;
      }

      id = R.id.toolbar_back;
      Toolbar toolbarBack = ViewBindings.findChildViewById(rootView, id);
      if (toolbarBack == null) {
        break missingId;
      }

      return new MovieDetailBinding((RelativeLayout) rootView, addReviewBtn, backdropGuideline,
          movieBackdrop, movieDetailRecyclerView, movieId, movieOverview, moviePoster,
          moviePosterCard, moviePosterTitleBarrier, movieRate, movieReleaseDate, movieReviewBarrier,
          movieTitle, nestedScrollView, toolbarBack);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
