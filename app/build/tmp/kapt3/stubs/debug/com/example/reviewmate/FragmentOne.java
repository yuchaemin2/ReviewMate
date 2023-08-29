package com.example.reviewmate;

import java.lang.System;

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0019H\u0002J&\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u0019H\u0002J\u0016\u0010%\u001a\u00020\u00192\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0\'H\u0002J\u0016\u0010)\u001a\u00020\u00192\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0\'H\u0002J\u0016\u0010*\u001a\u00020\u00192\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0\'H\u0002J\u0010\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020(H\u0002J\b\u0010-\u001a\u00020\u0019H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/example/reviewmate/FragmentOne;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/example/reviewmate/databinding/FragmentOneBinding;", "getBinding", "()Lcom/example/reviewmate/databinding/FragmentOneBinding;", "setBinding", "(Lcom/example/reviewmate/databinding/FragmentOneBinding;)V", "popularMovies", "Landroidx/recyclerview/widget/RecyclerView;", "popularMoviesAdapter", "Lcom/example/reviewmate/MovieAdapter;", "popularMoviesLayoutMgr", "Landroidx/recyclerview/widget/LinearLayoutManager;", "selectedDate", "", "selectedDate_add1", "topRatedMovies", "topRatedMoviesAdapter", "topRatedMoviesLayoutMgr", "upcomingMovies", "upcomingMoviesAdapter", "upcomingMoviesLayoutMgr", "getPopularMovies", "", "getTopRatedMovies", "getUpcomingMovies", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onError", "onPopularMoviesFetched", "movies", "", "Lcom/example/reviewmate/common/Movie;", "onTopRatedMoviesFetched", "onUpcomingMoviesFetched", "showMovieDetails", "movie", "updateReviewListForSelectedDate", "app_debug"})
public final class FragmentOne extends androidx.fragment.app.Fragment {
    private java.lang.String selectedDate = "0";
    private java.lang.String selectedDate_add1 = "0";
    public com.example.reviewmate.databinding.FragmentOneBinding binding;
    private androidx.recyclerview.widget.RecyclerView popularMovies;
    private com.example.reviewmate.MovieAdapter popularMoviesAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager popularMoviesLayoutMgr;
    private androidx.recyclerview.widget.RecyclerView topRatedMovies;
    private com.example.reviewmate.MovieAdapter topRatedMoviesAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager topRatedMoviesLayoutMgr;
    private androidx.recyclerview.widget.RecyclerView upcomingMovies;
    private com.example.reviewmate.MovieAdapter upcomingMoviesAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager upcomingMoviesLayoutMgr;
    
    public FragmentOne() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.reviewmate.databinding.FragmentOneBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull()
    com.example.reviewmate.databinding.FragmentOneBinding p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void updateReviewListForSelectedDate() {
    }
    
    private final void showMovieDetails(com.example.reviewmate.common.Movie movie) {
    }
    
    private final void getPopularMovies() {
    }
    
    private final void getTopRatedMovies() {
    }
    
    private final void getUpcomingMovies() {
    }
    
    private final void onPopularMoviesFetched(java.util.List<com.example.reviewmate.common.Movie> movies) {
    }
    
    private final void onTopRatedMoviesFetched(java.util.List<com.example.reviewmate.common.Movie> movies) {
    }
    
    private final void onUpcomingMoviesFetched(java.util.List<com.example.reviewmate.common.Movie> movies) {
    }
    
    private final void onError() {
    }
}