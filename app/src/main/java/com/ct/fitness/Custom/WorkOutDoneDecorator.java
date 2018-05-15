package com.ct.fitness.Custom;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;

/**
 * Created by Elia on 12/20/2017.
 */

public class WorkOutDoneDecorator implements DayViewDecorator {
    HashSet<CalendarDay> list;
    ColorDrawable colorDrawable;

    public WorkOutDoneDecorator(HashSet<CalendarDay> list) {
        this.list = list;
        colorDrawable=new ColorDrawable(Color.parseColor("#e57373"));

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return list.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
view.setBackgroundDrawable(colorDrawable);
    }
}
