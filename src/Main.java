import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {

        double amount = 1000.734;
        amount = guardPrecision(amount);
        String us = NumberFormat.getCurrencyInstance(Locale.CHINA).format(amount);
        System.out.println(us);

    }
    public static double guardPrecision(double d){
        d = d*100;
        d = Math.round(d);
        d = d/100;
        return d;
    }
    public static String findDay(int month, int day, int year) {

        Calendar calendar = Calendar.getInstance();
        Date date = new Date(year, month, day);
        calendar.setTime(date);
        String[] daysOftheWeek = new String[8];
        daysOftheWeek[1] = "SUNDAY";
        daysOftheWeek[2] = "MONDAY";
        daysOftheWeek[3] = "TUESDAY";
        daysOftheWeek[4] = "WEDNESDAY";
        daysOftheWeek[5] = "THURSDAY";
        daysOftheWeek[6] = "FRIDAY";
        daysOftheWeek[7] = "SATURDAY";


        return daysOftheWeek[calendar.get(Calendar.DAY_OF_WEEK)];
    }
}
