package com.example.reviewmate.common;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/example/reviewmate/common/MoviesRepository;", "", "()V", "Companion", "app_debug"})
public final class MoviesRepository {
    @org.jetbrains.annotations.NotNull
    public static final com.example.reviewmate.common.MoviesRepository.Companion Companion = null;
    
    public MoviesRepository() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002JG\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\'\u0010\u0007\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000fJO\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\'\u0010\u0007\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000fJG\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\'\u0010\u0007\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000fJG\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\'\u0010\u0007\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f\u00a8\u0006\u0015"}, d2 = {"Lcom/example/reviewmate/common/MoviesRepository$Companion;", "", "()V", "getPopularMovies", "", "page", "", "onSuccess", "Lkotlin/Function1;", "", "Lcom/example/reviewmate/common/Movie;", "Lkotlin/ParameterName;", "name", "movies", "onError", "Lkotlin/Function0;", "getSearchMovies", "query", "", "getTopRatedMovies", "getUpcomingMovies", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void getPopularMovies(int page, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super java.util.List<com.example.reviewmate.common.Movie>, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function0<kotlin.Unit> onError) {
        }
        
        public final void getTopRatedMovies(int page, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super java.util.List<com.example.reviewmate.common.Movie>, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function0<kotlin.Unit> onError) {
        }
        
        public final void getUpcomingMovies(int page, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super java.util.List<com.example.reviewmate.common.Movie>, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function0<kotlin.Unit> onError) {
        }
        
        public final void getSearchMovies(int page, @org.jetbrains.annotations.NotNull
        java.lang.String query, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super java.util.List<com.example.reviewmate.common.Movie>, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function0<kotlin.Unit> onError) {
        }
    }
}