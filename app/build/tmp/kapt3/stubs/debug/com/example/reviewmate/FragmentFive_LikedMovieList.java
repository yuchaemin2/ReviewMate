package com.example.reviewmate;

import java.lang.System;

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentFive_LikedMovieList.newInstance] factory method to
 * create an instance of this fragment.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u0013H\u0002J\u0012\u0010\u001a\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010#\u001a\u00020\u0013H\u0002J\b\u0010$\u001a\u00020\u0013H\u0002J\b\u0010%\u001a\u00020\u0013H\u0002J\u0010\u0010&\u001a\u00020\u00132\u0006\u0010\'\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/example/reviewmate/FragmentFive_LikedMovieList;", "Landroidx/fragment/app/Fragment;", "()V", "ListMoviesAdapter", "Lcom/example/reviewmate/MovieAdapter;", "ListMoviesLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "ListMoviesRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "binding", "Lcom/example/reviewmate/databinding/FragmentFiveLikedMovieListBinding;", "getBinding", "()Lcom/example/reviewmate/databinding/FragmentFiveLikedMovieListBinding;", "setBinding", "(Lcom/example/reviewmate/databinding/FragmentFiveLikedMovieListBinding;)V", "param1", "", "param2", "MoviesFetched", "", "movies", "", "Lcom/example/reviewmate/common/Movie;", "deleteAllUserLikedMovie", "userEmail", "getLikedMovies", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onError", "refreshRecyclerView", "showDeleteConfirmationDialog", "showMovieDetails", "movie", "Companion", "app_debug"})
public final class FragmentFive_LikedMovieList extends androidx.fragment.app.Fragment {
    private java.lang.String param1;
    private java.lang.String param2;
    public com.example.reviewmate.databinding.FragmentFiveLikedMovieListBinding binding;
    private androidx.recyclerview.widget.RecyclerView ListMoviesRecyclerView;
    private com.example.reviewmate.MovieAdapter ListMoviesAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager ListMoviesLayoutManager;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.reviewmate.FragmentFive_LikedMovieList.Companion Companion = null;
    
    public FragmentFive_LikedMovieList() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.reviewmate.databinding.FragmentFiveLikedMovieListBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull()
    com.example.reviewmate.databinding.FragmentFiveLikedMovieListBinding p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void showMovieDetails(com.example.reviewmate.common.Movie movie) {
    }
    
    private final void getLikedMovies() {
    }
    
    private final void MoviesFetched(java.util.List<com.example.reviewmate.common.Movie> movies) {
    }
    
    private final void onError() {
    }
    
    private final void deleteAllUserLikedMovie(java.lang.String userEmail) {
    }
    
    private final void refreshRecyclerView() {
    }
    
    private final void showDeleteConfirmationDialog() {
    }
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmemtFive_LikedMovieList.
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final com.example.reviewmate.FragmentFive_LikedMovieList newInstance(@org.jetbrains.annotations.NotNull()
    java.lang.String param1, @org.jetbrains.annotations.NotNull()
    java.lang.String param2) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/reviewmate/FragmentFive_LikedMovieList$Companion;", "", "()V", "newInstance", "Lcom/example/reviewmate/FragmentFive_LikedMovieList;", "param1", "", "param2", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmemtFive_LikedMovieList.
         */
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final com.example.reviewmate.FragmentFive_LikedMovieList newInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String param1, @org.jetbrains.annotations.NotNull()
        java.lang.String param2) {
            return null;
        }
    }
}