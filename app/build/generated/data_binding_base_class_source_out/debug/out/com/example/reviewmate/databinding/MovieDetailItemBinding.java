// Generated by view binder compiler. Do not edit!
package com.example.reviewmate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.reviewmate.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MovieDetailItemBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView date;

  @NonNull
  public final TextView rate;

  @NonNull
  public final ImageView user;

  @NonNull
  public final TextView userName;

  private MovieDetailItemBinding(@NonNull LinearLayout rootView, @NonNull TextView date,
      @NonNull TextView rate, @NonNull ImageView user, @NonNull TextView userName) {
    this.rootView = rootView;
    this.date = date;
    this.rate = rate;
    this.user = user;
    this.userName = userName;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MovieDetailItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MovieDetailItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.movie_detail_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MovieDetailItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.date;
      TextView date = ViewBindings.findChildViewById(rootView, id);
      if (date == null) {
        break missingId;
      }

      id = R.id.rate;
      TextView rate = ViewBindings.findChildViewById(rootView, id);
      if (rate == null) {
        break missingId;
      }

      id = R.id.user;
      ImageView user = ViewBindings.findChildViewById(rootView, id);
      if (user == null) {
        break missingId;
      }

      id = R.id.user_name;
      TextView userName = ViewBindings.findChildViewById(rootView, id);
      if (userName == null) {
        break missingId;
      }

      return new MovieDetailItemBinding((LinearLayout) rootView, date, rate, user, userName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}