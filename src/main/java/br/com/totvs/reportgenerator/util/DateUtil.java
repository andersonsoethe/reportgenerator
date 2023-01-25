package br.com.totvs.reportgenerator.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

    public int getDaysToFinishTask(Date dateCreated, Date dateResolved) {

        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(dateCreated);
        calendarStart.set(Calendar.HOUR, 0);
        calendarStart.set(Calendar.MINUTE, 0);
        calendarStart.set(Calendar.SECOND, 0);
        calendarStart.set(Calendar.MILLISECOND, 0);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(dateResolved);
        calendarEnd.set(Calendar.HOUR, 0);
        calendarEnd.set(Calendar.MINUTE, 0);
        calendarEnd.set(Calendar.SECOND, 0);
        calendarEnd.set(Calendar.MILLISECOND, 0);

        int workingDays = 0;

        while (!calendarStart.after(calendarEnd)) {
            if (calendarStart.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && calendarStart.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                workingDays += 1;
            }
            calendarStart.add(Calendar.DATE, 1);
        }

        return workingDays;
    }
}
