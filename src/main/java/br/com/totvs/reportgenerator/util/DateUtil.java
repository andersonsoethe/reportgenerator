package br.com.totvs.reportgenerator.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

    public int calcDaysToFinishTask(String dateCreated, String dateResolved) {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

            Date dateStart = dateFormat.parse(dateCreated);
            Date dateEnd = dateFormat.parse(dateResolved);
            System.out.println(dateStart);
            System.out.println(dateEnd);

            Calendar calendarStart = Calendar.getInstance();
            calendarStart.setTime(dateStart);
            calendarStart.set(Calendar.HOUR, 0);
            calendarStart.set(Calendar.MINUTE, 0);
            calendarStart.set(Calendar.SECOND, 0);
            calendarStart.set(Calendar.MILLISECOND, 0);

            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(dateEnd);
            calendarEnd.set(Calendar.HOUR, 0);
            calendarEnd.set(Calendar.MINUTE, 0);
            calendarEnd.set(Calendar.SECOND, 0);
            calendarEnd.set(Calendar.MILLISECOND, 0);

            int workingDays = 0;

            while (!calendarStart.after(calendarEnd)) {
                if (calendarStart.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendarStart.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    workingDays += 1;
                }
                calendarStart.add(Calendar.DATE, 1);
            }
            return workingDays;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public String getDateNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date = new Date();

        return dateFormat.format(date);
    }

    public String calcDeliveryTimeStatus(String timeSpend, String storyPoints, String status) {
        double goalTime = Double.valueOf(storyPoints).doubleValue();
        double realTime = Double.valueOf(timeSpend).doubleValue();
        if (realTime > goalTime) {
            return "LATE";
        } else {
            return "IN_TIME";
        }
    }

}
