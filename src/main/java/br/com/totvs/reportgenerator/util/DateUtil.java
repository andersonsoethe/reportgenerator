package br.com.totvs.reportgenerator.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

    public LocalDate dateFormatter(String date) throws ParseException {
        LocalDate localDate = LocalDate.parse(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
                .format(new SimpleDateFormat("dd/MM/yyy", Locale.US).parse(date)));

        return  localDate;
    }

    public int calcDaysToFinishTask (String dateCreated, String dateResolved){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss zzz yyyy", Locale.US);
        LocalDate dateStart = LocalDate.parse(dateCreated, formatter);
        LocalDate dateEnd = LocalDate.parse(dateResolved, formatter);
        System.out.println(dateStart);
        System.out.println(dateEnd);

        long days = ChronoUnit.DAYS.between(dateStart, dateEnd);
        System.out.println(days);

        int workingDays = 0;

        Calendar calendar = Calendar.getInstance();
        for (int i = 1; i < days ; i++) {
            calendar.add(Calendar.DATE, 1);
            if(calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
                workingDays +=1;
            }
        }
        return workingDays;

    }

}
