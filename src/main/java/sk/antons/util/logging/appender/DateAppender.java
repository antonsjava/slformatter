/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.antons.util.logging.appender;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.LogRecord;

/**
 *
 * @author antons
 */
public class DateAppender extends AbstractAppender {
    private long starttime = 0;
    private long endtime = 0;
    private String date = null;


    private synchronized String date(long millis) {
        if((starttime < millis) && (millis >= endtime)) date = null;
        if(date == null) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(millis);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            starttime = c.getTime().getTime();
            c.add(Calendar.DAY_OF_MONTH, 1);
            endtime = c.getTime().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            Date d = new Date(millis);
            date = df.format(d);
        }
        return date;
    }

    @Override
    protected String format(LogRecord record) {
        if(record == null) return null;
        return date(record.getMillis());
    }
    
}
