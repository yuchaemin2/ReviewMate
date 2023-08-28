package com.example.reviewmate;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\u0012\u0010\u001f\u001a\u00020\u001d2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J&\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010(\u001a\u00020\u001dH\u0016J\u000e\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\u0016J\u000e\u0010+\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\u0016J\u0006\u0010,\u001a\u00020\u001dJ\u0006\u0010-\u001a\u00020\u001dJ\u0006\u0010.\u001a\u00020\u001dJ\u0006\u0010/\u001a\u00020\u001dJ\u0006\u00100\u001a\u00020\u001dJ\u0006\u00101\u001a\u00020\u001dJ\u0006\u00102\u001a\u00020\u001dJ\u0006\u00103\u001a\u00020\u001dJ\u0006\u00104\u001a\u00020\u001dJ\u000e\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u00020\u000fR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001f\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u000e\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000e\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lcom/example/reviewmate/FragmentThree;", "Landroidx/fragment/app/Fragment;", "()V", "alertHandler", "Landroid/content/DialogInterface$OnClickListener;", "getAlertHandler", "()Landroid/content/DialogInterface$OnClickListener;", "binding", "Lcom/example/reviewmate/databinding/FragmentThreeBinding;", "getBinding", "()Lcom/example/reviewmate/databinding/FragmentThreeBinding;", "setBinding", "(Lcom/example/reviewmate/databinding/FragmentThreeBinding;)V", "characters", "", "", "getCharacters", "()[[Ljava/lang/String;", "[[Ljava/lang/String;", "imageView", "Landroid/widget/ImageView;", "imgResourceIds", "", "getImgResourceIds", "()[Ljava/lang/Integer;", "[Ljava/lang/Integer;", "param1", "param2", "downloadAndDisplayImage", "", "fetchLevel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onStart", "openCharacters", "level", "openDialog", "openLevel1", "openLevel2", "openLevel3", "openLevel4", "openLevel5", "openLevel6", "openLevel7", "openLevel8", "openLevel9", "upLoadProfileImg", "strImg", "app_debug"})
public final class FragmentThree extends androidx.fragment.app.Fragment {
    private java.lang.String param1;
    private java.lang.String param2;
    public com.example.reviewmate.databinding.FragmentThreeBinding binding;
    private android.widget.ImageView imageView;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String[][] characters = {{"/profile_images/level_1.png", "\ud558\ud488\ud558\ub294 \uace0\uc591\uc774", "\ud558\ud488\ud558\uace0 \ud53c\uace4\ud574 \ubcf4\uc774\ub294 \uace0\uc591\uc774\uc785\ub2c8\ub2e4."}, {"/profile_images/level_2.png", "\uadf8\ub8e8\ubc0d\ud558\ub294 \uace0\uc591\uc774", "\ud138\uc744 \uadf8\ub8e8\ubc0d\ud558\ub294 \uace0\uc591\uc774\uc785\ub2c8\ub2e4."}, {"/profile_images/level_3.png", "\uacbd\uacc4\ud558\ub294 \uace0\uc591\uc774", "\ud638\uae30\uc2ec \uac00\ub4dd\ud55c \ub208\uc73c\ub85c \uc8fc\ubcc0\uc744 \ub298 \uacbd\uacc4\ud558\ub294 \uace0\uc591\uc774\uc785\ub2c8\ub2e4."}, {"/profile_images/level_4.png", "\ud558\ud488\ud558\ub294 \uace0\uc591\uc774", "\uc878\ub824\uc11c \ud558\ud488\ud558\ub294 \uac80\uc815\uc0c9 \uace0\uc591\uc774\uc785\ub2c8\ub2e4."}, {"/profile_images/level_5.png", "\uc0bc\uc0c9\ucf69\ub5a1 \uace0\uc591\uc774", "\ub3c5\ud2b9\ud55c \uc0bc\uc0c9 \ubaa8\uc2b5\uc73c\ub85c \uc0ac\ub78c\ub4e4\uc758 \uad00\uc2ec\uc744 \ub044\ub294 \uace0\uc591\uc774\uc785\ub2c8\ub2e4."}, {"/profile_images/level_6.png", "\uba4d\ub54c\ub9ac\ub294 \uace0\uc591\uc774", "\ub208\uc744 \ubc18\ucbe4 \uac10\uace0 \uba3c \uacf3\uc744 \uc751\uc2dc\ud558\ub294 \ub4ef\ud55c \uace0\uc694\ud55c \uace0\uc591\uc774\uc785\ub2c8\ub2e4."}, {"/profile_images/level_7.png", "\ub178\ub824\ubcf4\ub294 \uace0\uc591\uc774", "\uae4c\uce60\ud55c \ub290\ub08c\uc73c\ub85c \uc8fc\ubcc0\uc744 \ubc14\ub77c\ubcf4\ub294 \uace0\uc591\uc774\uc785\ub2c8\ub2e4."}, {"/profile_images/level_8.png", "\ud587\ube5b\ucb10\ub294 \uace0\uc591\uc774", "\ub530\ub73b\ud55c \ud587\uc0b4 \uc544\ub798\uc5d0\uc11c \ud3b8\uc548\ud568\uc744 \ub290\ub07c\ub294 \uace0\uc591\uc774\uc785\ub2c8\ub2e4."}, {"/profile_images/level_9.png", "\ubbf8\uc18c\uc9d3\ub294 \uace0\uc591\uc774", "\ub9c8\uce58 \ubbf8\uc18c\ub97c \uc9d3\ub294 \ub4ef\ud55c \ud45c\uc815\uc73c\ub85c \uadc0\uc5ec\uc6c0\uc744 \ubf50\ub0b4\ub294 \uace0\uc591\uc774\uc785\ub2c8\ub2e4."}};
    @org.jetbrains.annotations.NotNull()
    private final java.lang.Integer[] imgResourceIds = {com.example.reviewmate.R.drawable.level_1, com.example.reviewmate.R.drawable.level_2, com.example.reviewmate.R.drawable.level_3, com.example.reviewmate.R.drawable.level_4, com.example.reviewmate.R.drawable.level_5, com.example.reviewmate.R.drawable.level_6, com.example.reviewmate.R.drawable.level_7, com.example.reviewmate.R.drawable.level_8, com.example.reviewmate.R.drawable.level_9};
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
    
    @java.lang.Override()
    public void onStart() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void fetchLevel() {
    }
    
    private final void downloadAndDisplayImage() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String[][] getCharacters() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.Integer[] getImgResourceIds() {
        return null;
    }
    
    public final void openLevel1() {
    }
    
    public final void openLevel2() {
    }
    
    public final void openLevel3() {
    }
    
    public final void openLevel4() {
    }
    
    public final void openLevel5() {
    }
    
    public final void openLevel6() {
    }
    
    public final void openLevel7() {
    }
    
    public final void openLevel8() {
    }
    
    public final void openLevel9() {
    }
    
    public final void openCharacters(int level) {
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