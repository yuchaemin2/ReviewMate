package com.example.reviewmate;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0006\u0010\u0016\u001a\u00020\u0013J\u0016\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\nR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u001e"}, d2 = {"Lcom/example/reviewmate/AddActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/reviewmate/databinding/ActivityAddBinding;", "getBinding", "()Lcom/example/reviewmate/databinding/ActivityAddBinding;", "setBinding", "(Lcom/example/reviewmate/databinding/ActivityAddBinding;)V", "filePath", "", "getFilePath", "()Ljava/lang/String;", "setFilePath", "(Ljava/lang/String;)V", "dateToString", "date", "Ljava/util/Date;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "saveStore", "updateCount", "docRef", "Lcom/google/firebase/firestore/DocumentReference;", "updatedValue", "", "uploadImage", "docId", "app_debug"})
public final class AddActivity extends androidx.appcompat.app.AppCompatActivity {
    public com.example.reviewmate.databinding.ActivityAddBinding binding;
    public java.lang.String filePath;
    
    public AddActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.reviewmate.databinding.ActivityAddBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull()
    com.example.reviewmate.databinding.ActivityAddBinding p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFilePath() {
        return null;
    }
    
    public final void setFilePath(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String dateToString(@org.jetbrains.annotations.NotNull()
    java.util.Date date) {
        return null;
    }
    
    public final void saveStore() {
    }
    
    public final void updateCount(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.DocumentReference docRef, long updatedValue) {
    }
    
    public final void uploadImage(@org.jetbrains.annotations.NotNull()
    java.lang.String docId) {
    }
}