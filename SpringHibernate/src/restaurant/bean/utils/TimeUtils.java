package restaurant.bean.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    public static Calendar getCurrentCalendar() {
        return Calendar.getInstance(Locale.CHINA);
    }
    
    /* Return hour difference of d2 - d1 */
    public static int calculateDiffHours(Date d1, Date d2) {
        if (d2.before(d1)) {
            return 0;
        }
        
        Calendar cal = TimeUtils.getCurrentCalendar();
        cal.setTime(d2);
        int d2year = cal.get(Calendar.YEAR);
        int d2day = cal.get(Calendar.DAY_OF_YEAR);
        int d2hour = cal.get(Calendar.HOUR_OF_DAY);
        int d2mini = cal.get(Calendar.MINUTE);
        cal.setTime(d1);
        int d1year = cal.get(Calendar.YEAR);
        int d1day = cal.get(Calendar.DAY_OF_YEAR);
        int d1hour = cal.get(Calendar.HOUR_OF_DAY);
        int d1mini = cal.get(Calendar.MINUTE);
        
        int result = (365 * 24 * (d2year - d1year)) +
                     (24 * (d2day-d1day)) + (d2hour - d1hour) + 
                     ((d2mini - d1mini >= 30) ? 1 : 0);
        
        return result;
    }
    
    public static Date buildDate(int year, int month, int date) {
        Calendar cal = TimeUtils.getCurrentCalendar();
        cal.set(year, month, date);
        
        return cal.getTime();
    }
}
