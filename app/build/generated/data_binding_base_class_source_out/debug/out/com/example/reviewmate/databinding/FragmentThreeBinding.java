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

public final class FragmentThreeBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView level1;

  @NonNull
  public final TextView level1Text;

  @NonNull
  public final ImageView level2;

  @NonNull
  public final TextView level2Text;

  @NonNull
  public final ImageView level3;

  @NonNull
  public final TextView level3Text;

  @NonNull
  public final ImageView level4;

  @NonNull
  public final TextView level4Text;

  @NonNull
  public final ImageView level5;

  @NonNull
  public final TextView level5Text;

  @NonNull
  public final ImageView level6;

  @NonNull
  public final TextView level6Text;

  @NonNull
  public final ImageView level7;

  @NonNull
  public final TextView level7Text;

  @NonNull
  public final ImageView level8;

  @NonNull
  public final TextView level8Text;

  @NonNull
  public final ImageView level9;

  @NonNull
  public final TextView level9Text;

  @NonNull
  public final TextView userLevel;

  @NonNull
  public final ImageView userProfile;

  private FragmentThreeBinding(@NonNull LinearLayout rootView, @NonNull ImageView level1,
      @NonNull TextView level1Text, @NonNull ImageView level2, @NonNull TextView level2Text,
      @NonNull ImageView level3, @NonNull TextView level3Text, @NonNull ImageView level4,
      @NonNull TextView level4Text, @NonNull ImageView level5, @NonNull TextView level5Text,
      @NonNull ImageView level6, @NonNull TextView level6Text, @NonNull ImageView level7,
      @NonNull TextView level7Text, @NonNull ImageView level8, @NonNull TextView level8Text,
      @NonNull ImageView level9, @NonNull TextView level9Text, @NonNull TextView userLevel,
      @NonNull ImageView userProfile) {
    this.rootView = rootView;
    this.level1 = level1;
    this.level1Text = level1Text;
    this.level2 = level2;
    this.level2Text = level2Text;
    this.level3 = level3;
    this.level3Text = level3Text;
    this.level4 = level4;
    this.level4Text = level4Text;
    this.level5 = level5;
    this.level5Text = level5Text;
    this.level6 = level6;
    this.level6Text = level6Text;
    this.level7 = level7;
    this.level7Text = level7Text;
    this.level8 = level8;
    this.level8Text = level8Text;
    this.level9 = level9;
    this.level9Text = level9Text;
    this.userLevel = userLevel;
    this.userProfile = userProfile;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentThreeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentThreeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_three, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentThreeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.level_1;
      ImageView level1 = ViewBindings.findChildViewById(rootView, id);
      if (level1 == null) {
        break missingId;
      }

      id = R.id.level_1_text;
      TextView level1Text = ViewBindings.findChildViewById(rootView, id);
      if (level1Text == null) {
        break missingId;
      }

      id = R.id.level_2;
      ImageView level2 = ViewBindings.findChildViewById(rootView, id);
      if (level2 == null) {
        break missingId;
      }

      id = R.id.level_2_text;
      TextView level2Text = ViewBindings.findChildViewById(rootView, id);
      if (level2Text == null) {
        break missingId;
      }

      id = R.id.level_3;
      ImageView level3 = ViewBindings.findChildViewById(rootView, id);
      if (level3 == null) {
        break missingId;
      }

      id = R.id.level_3_text;
      TextView level3Text = ViewBindings.findChildViewById(rootView, id);
      if (level3Text == null) {
        break missingId;
      }

      id = R.id.level_4;
      ImageView level4 = ViewBindings.findChildViewById(rootView, id);
      if (level4 == null) {
        break missingId;
      }

      id = R.id.level_4_text;
      TextView level4Text = ViewBindings.findChildViewById(rootView, id);
      if (level4Text == null) {
        break missingId;
      }

      id = R.id.level_5;
      ImageView level5 = ViewBindings.findChildViewById(rootView, id);
      if (level5 == null) {
        break missingId;
      }

      id = R.id.level_5_text;
      TextView level5Text = ViewBindings.findChildViewById(rootView, id);
      if (level5Text == null) {
        break missingId;
      }

      id = R.id.level_6;
      ImageView level6 = ViewBindings.findChildViewById(rootView, id);
      if (level6 == null) {
        break missingId;
      }

      id = R.id.level_6_text;
      TextView level6Text = ViewBindings.findChildViewById(rootView, id);
      if (level6Text == null) {
        break missingId;
      }

      id = R.id.level_7;
      ImageView level7 = ViewBindings.findChildViewById(rootView, id);
      if (level7 == null) {
        break missingId;
      }

      id = R.id.level_7_text;
      TextView level7Text = ViewBindings.findChildViewById(rootView, id);
      if (level7Text == null) {
        break missingId;
      }

      id = R.id.level_8;
      ImageView level8 = ViewBindings.findChildViewById(rootView, id);
      if (level8 == null) {
        break missingId;
      }

      id = R.id.level_8_text;
      TextView level8Text = ViewBindings.findChildViewById(rootView, id);
      if (level8Text == null) {
        break missingId;
      }

      id = R.id.level_9;
      ImageView level9 = ViewBindings.findChildViewById(rootView, id);
      if (level9 == null) {
        break missingId;
      }

      id = R.id.level_9_text;
      TextView level9Text = ViewBindings.findChildViewById(rootView, id);
      if (level9Text == null) {
        break missingId;
      }

      id = R.id.userLevel;
      TextView userLevel = ViewBindings.findChildViewById(rootView, id);
      if (userLevel == null) {
        break missingId;
      }

      id = R.id.userProfile;
      ImageView userProfile = ViewBindings.findChildViewById(rootView, id);
      if (userProfile == null) {
        break missingId;
      }

      return new FragmentThreeBinding((LinearLayout) rootView, level1, level1Text, level2,
          level2Text, level3, level3Text, level4, level4Text, level5, level5Text, level6,
          level6Text, level7, level7Text, level8, level8Text, level9, level9Text, userLevel,
          userProfile);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}