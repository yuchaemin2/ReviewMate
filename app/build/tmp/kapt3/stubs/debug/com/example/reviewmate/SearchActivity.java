package com.example.reviewmate;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001+B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\u0012\u0010 \u001a\u00020\u001e2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\u001eH\u0002J\u0016\u0010$\u001a\u00020\u001e2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\'0&H\u0002J\b\u0010(\u001a\u00020\u001eH\u0002J\u0010\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\'H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/example/reviewmate/SearchActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/reviewmate/databinding/ActivitySearchBinding;", "getBinding", "()Lcom/example/reviewmate/databinding/ActivitySearchBinding;", "setBinding", "(Lcom/example/reviewmate/databinding/ActivitySearchBinding;)V", "root", "Landroid/view/View;", "getRoot", "()Landroid/view/View;", "setRoot", "(Landroid/view/View;)V", "searchKeyword", "", "getSearchKeyword", "()Ljava/lang/String;", "setSearchKeyword", "(Ljava/lang/String;)V", "searchMovies", "Landroidx/recyclerview/widget/RecyclerView;", "searchMoviesAdapter", "Lcom/example/reviewmate/MovieAdapter;", "searchMoviesLayoutMgr", "Landroidx/recyclerview/widget/LinearLayoutManager;", "searchMoviesPage", "", "attachSearchMoviesOnScrollListener", "", "getSearchMovies", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onError", "onSearchMoviesFetched", "movies", "", "Lcom/example/reviewmate/common/Movie;", "removeData", "showMovieDetails", "movie", "GridSpacingItemDecoration", "app_debug"})
public final class SearchActivity extends androidx.appcompat.app.AppCompatActivity {
    public android.view.View root;
    @org.jetbrains.annotations.NotNull
    private java.lang.String searchKeyword = "";
    private androidx.recyclerview.widget.RecyclerView searchMovies;
    private com.example.reviewmate.MovieAdapter searchMoviesAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager searchMoviesLayoutMgr;
    private int searchMoviesPage = 1;
    public com.example.reviewmate.databinding.ActivitySearchBinding binding;
    
    public SearchActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.view.View getRoot() {
        return null;
    }
    
    public final void setRoot(@org.jetbrains.annotations.NotNull
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSearchKeyword() {
        return null;
    }
    
    public final void setSearchKeyword(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.reviewmate.databinding.ActivitySearchBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull
    com.example.reviewmate.databinding.ActivitySearchBinding p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void removeData() {
    }
    
    private final void getSearchMovies() {
    }
    
    private final void attachSearchMoviesOnScrollListener() {
    }
    
    private final void onSearchMoviesFetched(java.util.List<com.example.reviewmate.common.Movie> movies) {
    }
    
    private final void onError() {
    }
    
    private final void showMovieDetails(com.example.reviewmate.common.Movie movie) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/reviewmate/SearchActivity$GridSpacingItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "spanCount", "", "spacing", "includeEdge", "", "(IIZ)V", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "app_debug"})
    public static final class GridSpacingItemDecoration extends androidx.recyclerview.widget.RecyclerView.ItemDecoration {
        private final int spanCount = 0;
        private final int spacing = 0;
        private final boolean includeEdge = false;
        
        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            super();
        }
        
        @java.lang.Override
        public void getItemOffsets(@org.jetbrains.annotations.NotNull
        android.graphics.Rect outRect, @org.jetbrains.annotations.NotNull
        android.view.View view, @org.jetbrains.annotations.NotNull
        androidx.recyclerview.widget.RecyclerView parent, @org.jetbrains.annotations.NotNull
        androidx.recyclerview.widget.RecyclerView.State state) {
        }
    }
}