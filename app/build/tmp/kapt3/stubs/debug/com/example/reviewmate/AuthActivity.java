package com.example.reviewmate;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u0016\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fJ\u0016\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0015"}, d2 = {"Lcom/example/reviewmate/AuthActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/reviewmate/databinding/ActivityAuthBinding;", "getBinding", "()Lcom/example/reviewmate/databinding/ActivityAuthBinding;", "setBinding", "(Lcom/example/reviewmate/databinding/ActivityAuthBinding;)V", "changeVisibility", "", "mode", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "updateEmail", "docRef", "Lcom/google/firebase/firestore/DocumentReference;", "updatedValue", "updateProfile", "app_debug"})
public final class AuthActivity extends androidx.appcompat.app.AppCompatActivity {
    public com.example.reviewmate.databinding.ActivityAuthBinding binding;
    
    public AuthActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.reviewmate.databinding.ActivityAuthBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull
    com.example.reviewmate.databinding.ActivityAuthBinding p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    public final void updateEmail(@org.jetbrains.annotations.NotNull
    com.google.firebase.firestore.DocumentReference docRef, @org.jetbrains.annotations.NotNull
    java.lang.String updatedValue) {
    }
    
    public final void updateProfile(@org.jetbrains.annotations.NotNull
    com.google.firebase.firestore.DocumentReference docRef, @org.jetbrains.annotations.NotNull
    java.lang.String updatedValue) {
    }
    
    public final void changeVisibility(@org.jetbrains.annotations.NotNull
    java.lang.String mode) {
    }
}