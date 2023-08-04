package com.example.reviewmate;

import java.lang.System;

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentThree.newInstance] factory method to
 * create an instance of this fragment.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0016H\u0016J\u000e\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\"J\u000e\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u000fJ\u0006\u0010&\u001a\u00020\u0016R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001f\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u000e\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/example/reviewmate/FragmentThree;", "Landroidx/fragment/app/Fragment;", "()V", "alertHandler", "Landroid/content/DialogInterface$OnClickListener;", "getAlertHandler", "()Landroid/content/DialogInterface$OnClickListener;", "binding", "Lcom/example/reviewmate/databinding/FragmentThreeBinding;", "getBinding", "()Lcom/example/reviewmate/databinding/FragmentThreeBinding;", "setBinding", "(Lcom/example/reviewmate/databinding/FragmentThreeBinding;)V", "characters", "", "", "getCharacters", "()[[Ljava/lang/String;", "[[Ljava/lang/String;", "param1", "param2", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onStart", "openCharacters", "level", "", "openDialog", "upLoadProfileImg", "strImg", "updateUserProfileImage", "app_debug"})
public final class FragmentThree extends androidx.fragment.app.Fragment {
    private java.lang.String param1;
    private java.lang.String param2;
    public com.example.reviewmate.databinding.FragmentThreeBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String[][] characters = {{"/profile_images/danielle_1.png", "\uc774\ub9841", "\uc124\uba851"}, {"/profile_images/danielle_2.png", "\uc774\ub9842", "\uc124\uba852"}, {"/profile_images/haerin_1.png", "\uc774\ub9843", "\uc124\uba853"}, {"/profile_images/haerin_2.png", "\uc774\ub9844", "\uc124\uba854"}, {"/profile_images/hanni_1.png", "\uc774\ub9845", "\uc124\uba855"}, {"/profile_images/hanni_2.png", "\uc774\ub9846", "\uc124\uba856"}, {"/profile_images/hyein_1.png", "\uc774\ub9847", "\uc124\uba857"}, {"/profile_images/hyein_2.png", "\uc774\ub9848", "\uc124\uba858"}, {"/profile_images/minji_1.png", "\uc774\ub9849", "\uc124\uba859"}};
    @org.jetbrains.annotations.NotNull()
    private final android.content.DialogInterface.OnClickListener alertHandler = null;
    
    public FragmentThree() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.reviewmate.databinding.FragmentThreeBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull()
    com.example.reviewmate.databinding.FragmentThreeBinding p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String[][] getCharacters() {
        return null;
    }
    
    public final void openCharacters(int level) {
    }
    
    public final void updateUserProfileImage() {
    }
    
    public final void openDialog(int level) {
    }
    
    public final void upLoadProfileImg(@org.jetbrains.annotations.NotNull()
    java.lang.String strImg) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.DialogInterface.OnClickListener getAlertHandler() {
        return null;
    }
}