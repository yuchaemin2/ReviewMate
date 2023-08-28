// Generated by view binder compiler. Do not edit!
package com.example.reviewmate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.reviewmate.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentOneBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final TextView HomeEmailView;

  @NonNull
  public final Button btnGoSearch;

  @NonNull
  public final CalendarView calendarView;

  @NonNull
  public final RecyclerView feedRecyclerView;

  @NonNull
  public final ScrollView fragmentOneLayout;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final LinearLayout linearLayout3;

  @NonNull
  public final RecyclerView popularMovies;

  @NonNull
  public final TextView textView1;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final RecyclerView topRatedMovies;

  @NonNull
  public final RecyclerView upcomingMovies;

  private FragmentOneBinding(@NonNull FrameLayout rootView, @NonNull TextView HomeEmailView,
      @NonNull Button btnGoSearch, @NonNull CalendarView calendarView,
      @NonNull RecyclerView feedRecyclerView, @NonNull ScrollView fragmentOneLayout,
      @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2,
      @NonNull LinearLayout linearLayout3, @NonNull RecyclerView popularMovies,
      @NonNull TextView textView1, @NonNull TextView textView2, @NonNull TextView textView3,
      @NonNull RecyclerView topRatedMovies, @NonNull RecyclerView upcomingMovies) {
    this.rootView = rootView;
    this.HomeEmailView = HomeEmailView;
    this.btnGoSearch = btnGoSearch;
    this.calendarView = calendarView;
    this.feedRecyclerView = feedRecyclerView;
    this.fragmentOneLayout = fragmentOneLayout;
    this.linearLayout = linearLayout;
    this.linearLayout2 = linearLayout2;
    this.linearLayout3 = linearLayout3;
    this.popularMovies = popularMovies;
    this.textView1 = textView1;
    this.textView2 = textView2;
    this.textView3 = textView3;
    this.topRatedMovies = topRatedMovies;
    this.upcomingMovies = upcomingMovies;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentOneBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentOneBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_one, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentOneBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.HomeEmailView;
      TextView HomeEmailView = ViewBindings.findChildViewById(rootView, id);
      if (HomeEmailView == null) {
        break missingId;
      }

      id = R.id.btn_go_search;
      Button btnGoSearch = ViewBindings.findChildViewById(rootView, id);
      if (btnGoSearch == null) {
        break missingId;
      }

      id = R.id.calendarView;
      CalendarView calendarView = ViewBindings.findChildViewById(rootView, id);
      if (calendarView == null) {
        break missingId;
      }

      id = R.id.feedRecyclerView;
      RecyclerView feedRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (feedRecyclerView == null) {
        break missingId;
      }

      id = R.id.fragmentOne_layout;
      ScrollView fragmentOneLayout = ViewBindings.findChildViewById(rootView, id);
      if (fragmentOneLayout == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.linearLayout3;
      LinearLayout linearLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout3 == null) {
        break missingId;
      }

      id = R.id.popular_movies;
      RecyclerView popularMovies = ViewBindings.findChildViewById(rootView, id);
      if (popularMovies == null) {
        break missingId;
      }

      id = R.id.textView1;
      TextView textView1 = ViewBindings.findChildViewById(rootView, id);
      if (textView1 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.top_rated_movies;
      RecyclerView topRatedMovies = ViewBindings.findChildViewById(rootView, id);
      if (topRatedMovies == null) {
        break missingId;
      }

      id = R.id.upcoming_movies;
      RecyclerView upcomingMovies = ViewBindings.findChildViewById(rootView, id);
      if (upcomingMovies == null) {
        break missingId;
      }

      return new FragmentOneBinding((FrameLayout) rootView, HomeEmailView, btnGoSearch,
          calendarView, feedRecyclerView, fragmentOneLayout, linearLayout, linearLayout2,
          linearLayout3, popularMovies, textView1, textView2, textView3, topRatedMovies,
          upcomingMovies);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}