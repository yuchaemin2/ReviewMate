// Generated by view binder compiler. Do not edit!
package com.example.reviewmate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.reviewmate.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySearchBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button bSearch;

  @NonNull
  public final EditText eSearchWord;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final RecyclerView searchMovies;

  private ActivitySearchBinding(@NonNull LinearLayout rootView, @NonNull Button bSearch,
      @NonNull EditText eSearchWord, @NonNull LinearLayout linearLayout,
      @NonNull LinearLayout linearLayout2, @NonNull RecyclerView searchMovies) {
    this.rootView = rootView;
    this.bSearch = bSearch;
    this.eSearchWord = eSearchWord;
    this.linearLayout = linearLayout;
    this.linearLayout2 = linearLayout2;
    this.searchMovies = searchMovies;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_search, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySearchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bSearch;
      Button bSearch = ViewBindings.findChildViewById(rootView, id);
      if (bSearch == null) {
        break missingId;
      }

      id = R.id.eSearchWord;
      EditText eSearchWord = ViewBindings.findChildViewById(rootView, id);
      if (eSearchWord == null) {
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

      id = R.id.search_movies;
      RecyclerView searchMovies = ViewBindings.findChildViewById(rootView, id);
      if (searchMovies == null) {
        break missingId;
      }

      return new ActivitySearchBinding((LinearLayout) rootView, bSearch, eSearchWord, linearLayout,
          linearLayout2, searchMovies);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
