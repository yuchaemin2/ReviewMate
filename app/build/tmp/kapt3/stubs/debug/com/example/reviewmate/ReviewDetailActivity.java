package com.example.reviewmate;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020,J\u0012\u0010-\u001a\u00020,2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\u0012\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103H\u0016J\u0010\u00104\u001a\u0002012\u0006\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u00020,H\u0014J\u0006\u00108\u001a\u00020,J\u0006\u00109\u001a\u00020,J\u0016\u0010:\u001a\u00020,2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR \u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001aX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001d\"\u0004\b#\u0010\u001fR\u0010\u0010$\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020\u0014X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0016\"\u0004\b\'\u0010\u0018\u00a8\u0006?"}, d2 = {"Lcom/example/reviewmate/ReviewDetailActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/reviewmate/MyCommentAdapter;", "adapterF", "Lcom/example/reviewmate/MyFeedAdapter;", "binding", "Lcom/example/reviewmate/databinding/ActivityReviewDetailBinding;", "getBinding", "()Lcom/example/reviewmate/databinding/ActivityReviewDetailBinding;", "setBinding", "(Lcom/example/reviewmate/databinding/ActivityReviewDetailBinding;)V", "file", "Ljava/io/File;", "getFile", "()Ljava/io/File;", "setFile", "(Ljava/io/File;)V", "filePath", "", "getFilePath", "()Ljava/lang/String;", "setFilePath", "(Ljava/lang/String;)V", "itemList", "", "Lcom/example/reviewmate/ItemCommentModel;", "getItemList", "()Ljava/util/List;", "setItemList", "(Ljava/util/List;)V", "itemListF", "Lcom/example/reviewmate/ItemFeedModel;", "getItemListF", "setItemListF", "myName", "reviewId", "getReviewId", "setReviewId", "dateToString", "date", "Ljava/util/Date;", "getStore", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "saveStore", "setProfileImage", "updateCount", "docRef", "Lcom/google/firebase/firestore/DocumentReference;", "updatedValue", "", "app_debug"})
public final class ReviewDetailActivity extends androidx.appcompat.app.AppCompatActivity {
    public com.example.reviewmate.databinding.ActivityReviewDetailBinding binding;
    private java.lang.String myName;
    public java.util.List<com.example.reviewmate.ItemCommentModel> itemList;
    public java.util.List<com.example.reviewmate.ItemFeedModel> itemListF;
    private com.example.reviewmate.MyCommentAdapter adapter;
    private com.example.reviewmate.MyFeedAdapter adapterF;
    public java.lang.String reviewId;
    public java.io.File file;
    public java.lang.String filePath;
    
    public ReviewDetailActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.reviewmate.databinding.ActivityReviewDetailBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull
    com.example.reviewmate.databinding.ActivityReviewDetailBinding p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.reviewmate.ItemCommentModel> getItemList() {
        return null;
    }
    
    public final void setItemList(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.reviewmate.ItemCommentModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.reviewmate.ItemFeedModel> getItemListF() {
        return null;
    }
    
    public final void setItemListF(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.reviewmate.ItemFeedModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getReviewId() {
        return null;
    }
    
    public final void setReviewId(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.io.File getFile() {
        return null;
    }
    
    public final void setFile(@org.jetbrains.annotations.NotNull
    java.io.File p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getFilePath() {
        return null;
    }
    
    public final void setFilePath(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    public final void setProfileImage() {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    public final void updateCount(@org.jetbrains.annotations.NotNull
    com.google.firebase.firestore.DocumentReference docRef, long updatedValue) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String dateToString(@org.jetbrains.annotations.NotNull
    java.util.Date date) {
        return null;
    }
    
    public final void saveStore() {
    }
    
    public final void getStore() {
    }
    
    @java.lang.Override
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.Nullable
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull
    android.view.MenuItem item) {
        return false;
    }
}