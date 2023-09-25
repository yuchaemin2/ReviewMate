package com.example.reviewmate;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u00a2\u0006\u0002\u0010\bB\u0005\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/reviewmate/EventDecorator;", "Lcom/prolificinteractive/materialcalendarview/DayViewDecorator;", "color", "", "reviewDates", "Ljava/util/HashSet;", "Lcom/prolificinteractive/materialcalendarview/CalendarDay;", "Lkotlin/collections/HashSet;", "(ILjava/util/HashSet;)V", "()V", "dates", "decorate", "", "view", "Lcom/prolificinteractive/materialcalendarview/DayViewFacade;", "shouldDecorate", "", "day", "app_debug"})
public final class EventDecorator implements com.prolificinteractive.materialcalendarview.DayViewDecorator {
    private int color = android.graphics.Color.GREEN;
    private java.util.HashSet<com.prolificinteractive.materialcalendarview.CalendarDay> dates;
    
    public EventDecorator() {
        super();
    }
    
    public EventDecorator(int color, @org.jetbrains.annotations.NotNull()
    java.util.HashSet<com.prolificinteractive.materialcalendarview.CalendarDay> reviewDates) {
        super();
    }
    
    @java.lang.Override()
    public boolean shouldDecorate(@org.jetbrains.annotations.Nullable()
    com.prolificinteractive.materialcalendarview.CalendarDay day) {
        return false;
    }
    
    @java.lang.Override()
    public void decorate(@org.jetbrains.annotations.Nullable()
    com.prolificinteractive.materialcalendarview.DayViewFacade view) {
    }
}