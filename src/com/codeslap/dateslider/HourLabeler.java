package com.codeslap.dateslider;

import java.util.Calendar;

/**
 * @author cristian
 * @version 1.0
 */
class HourLabeler extends DateSlider.Labeler {
    public HourLabeler(DateSlider dateSlider) {
        super(dateSlider);
    }

    @Override
    public DateSlider.TimeObject add(long time, int val) {
        Calendar c = Calendar.getInstance(getDateSlider().getTimeZone());
        c.setTimeInMillis(time);
        c.add(Calendar.HOUR_OF_DAY, val);
        return timeObjectFromCalendar(c);
    }

    @Override
    protected DateSlider.TimeObject timeObjectFromCalendar(Calendar c) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        // get the first millisecond of that hour
        c.set(year, month, day, hour, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        long startTime = c.getTimeInMillis();
        // get the last millisecond of that hour
        c.set(year, month, day, hour, 59, 59);
        c.set(Calendar.MILLISECOND, 999);
        long endTime = c.getTimeInMillis();
        return new DateSlider.TimeObject(String.valueOf(hour), startTime, endTime);
    }
}
