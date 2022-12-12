package br.com.totvs.reportgenerator.util;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//import javax.xml.datatype.XMLGregorianCalendar;

public class DataUtil {
//
//        private DataUtil() {
//            //NOP
//        }
//
//        private static final String[] DATE_FORMATS = new String[] {"dd/MM/yy", "dd/MM/yy HH:mm:ss", "dd/MM/yyyy", "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm", "yy-MM-dd", "yy-MM-dd HH:mm:ss", "yy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm"};
//
//        public static String toString(Date data) {
//            if (data == null) {
//                return null;
//            }
//
//            return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
//        }
//
//        public static Date toDate(String data) {
//            if (data == null) {
//                return null;
//            }
//
//            try {
//                return DateUtils.parseDate(data, DATE_FORMATS);
//            } catch (ParseException e) {
//                return null;
//            }
//        }
//
//        public static Date toDate(XMLGregorianCalendar data) {
//            if (data == null) {
//                return null;
//            }
//
//            Calendar calendar = data.toGregorianCalendar();
//            calendar.set(Calendar.HOUR_OF_DAY, 0);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            calendar.set(Calendar.MILLISECOND, 0);
//
//            return calendar.getTime();
//        }
//
//        public static Date zerarHora(Date data) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(data);
//            calendar.set(Calendar.HOUR_OF_DAY, 0);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            calendar.set(Calendar.MILLISECOND, 0);
//
//            return calendar.getTime();
//        }
//
//        public static Date finalHora(Date data) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(data);
//            calendar.set(Calendar.HOUR_OF_DAY, 23);
//            calendar.set(Calendar.MINUTE, 59);
//            calendar.set(Calendar.SECOND, 59);
//            calendar.set(Calendar.MILLISECOND, 999);
//
//            return calendar.getTime();
//        }
//
//        public static String toAnoMes(Date data) {
//            return new SimpleDateFormat("yyyy/MM").format(data);
//        }
    }

