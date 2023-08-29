package com.example.reviewmate;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001!B6\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\u0015\u001a\u00020\u000b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u001c\u0010\u0019\u001a\u00020\u000b2\n\u0010\u001a\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0018H\u0016J\u001c\u0010\u001c\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0018H\u0016J\u0014\u0010 \u001a\u00020\u000b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R5\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\""}, d2 = {"Lcom/example/reviewmate/MovieAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/reviewmate/MovieAdapter$MovieViewHolder;", "movies", "", "Lcom/example/reviewmate/common/Movie;", "onMovieClick", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "movie", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "getMovies", "()Ljava/util/List;", "setMovies", "(Ljava/util/List;)V", "getOnMovieClick", "()Lkotlin/jvm/functions/Function1;", "setOnMovieClick", "(Lkotlin/jvm/functions/Function1;)V", "appendMovies", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "removeMovies", "MovieViewHolder", "app_debug"})
public final class MovieAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.reviewmate.MovieAdapter.MovieViewHolder> {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.reviewmate.common.Movie> movies;
    @org.jetbrains.annotations.NotNull
    private kotlin.jvm.functions.Function1<? super com.example.reviewmate.common.Movie, kotlin.Unit> onMovieClick;
    
    public MovieAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.reviewmate.common.Movie> movies, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.reviewmate.common.Movie, kotlin.Unit> onMovieClick) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.reviewmate.common.Movie> getMovies() {
        return null;
    }
    
    public final void setMovies(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.reviewmate.common.Movie> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function1<com.example.reviewmate.common.Movie, kotlin.Unit> getOnMovieClick() {
        return null;
    }
    
    public final void setOnMovieClick(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.reviewmate.common.Movie, kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.example.reviewmate.MovieAdapter.MovieViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.reviewmate.MovieAdapter.MovieViewHolder holder, int position) {
    }
    
    public final void removeMovies(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.reviewmate.common.Movie> movies) {
    }
    
    public final void appendMovies(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.reviewmate.common.Movie> movies) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/example/reviewmate/MovieAdapter$MovieViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/reviewmate/databinding/ItemMovieBinding;", "(Lcom/example/reviewmate/MovieAdapter;Lcom/example/reviewmate/databinding/ItemMovieBinding;)V", "getBinding", "()Lcom/example/reviewmate/databinding/ItemMovieBinding;", "poster", "Landroid/widget/ImageView;", "bind", "", "movie", "Lcom/example/reviewmate/common/Movie;", "app_debug"})
    public final class MovieViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.reviewmate.databinding.ItemMovieBinding binding = null;
        private final android.widget.ImageView poster = null;
        
        public MovieViewHolder(@org.jetbrains.annotations.NotNull
        com.example.reviewmate.databinding.ItemMovieBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.reviewmate.databinding.ItemMovieBinding getBinding() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.example.reviewmate.common.Movie movie) {
        }
    }
}