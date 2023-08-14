package com.example.reviewmate;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0012H\u0014J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001f"}, d2 = {"Lcom/example/reviewmate/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/reviewmate/databinding/ActivityMainBinding;", "getBinding", "()Lcom/example/reviewmate/databinding/ActivityMainBinding;", "setBinding", "(Lcom/example/reviewmate/databinding/ActivityMainBinding;)V", "bottomNavigationView", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "html", "", "getHtml", "()Ljava/lang/String;", "setHtml", "(Ljava/lang/String;)V", "loadFragment", "", "fragment", "Landroidx/fragment/app/Fragment;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "updateIcons", "selectedItem", "Landroid/view/MenuItem;", "selectedIconRes", "", "Companion", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView;
    public com.example.reviewmate.databinding.ActivityMainBinding binding;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String html = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX=244148.546388&tmY=412423.75772&pageNo=1&numOfRows=10&ServiceKey=\uc11c\ube44\uc2a4\ud0a4";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.reviewmate.MainActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MOVIE_ID = "extra_movie_id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MOVIE_POSTER = "extra_movie_poster";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MOVIE_TITLE = "extra_movie_title";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MOVIE_RATING = "extra_movie_rating";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MOVIE_OVERVIEW = "extra_movie_overview";
    
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.reviewmate.databinding.ActivityMainBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull()
    com.example.reviewmate.databinding.ActivityMainBinding p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHtml() {
        return null;
    }
    
    public final void setHtml(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    private final void updateIcons(android.view.MenuItem selectedItem, int selectedIconRes) {
    }
    
    private final void loadFragment(androidx.fragment.app.Fragment fragment) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/reviewmate/MainActivity$Companion;", "", "()V", "MOVIE_ID", "", "MOVIE_OVERVIEW", "MOVIE_POSTER", "MOVIE_RATING", "MOVIE_TITLE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}