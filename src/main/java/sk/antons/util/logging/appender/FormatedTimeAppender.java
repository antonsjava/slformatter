/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.antons.util.logging.appender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;

/**
 *
 * @author antons
 */
public class FormatedTimeAppender extends AbstractAppender {
    private String format = null;

    @Override
    public void consolidate() {
        super.consolidate();
        if(param == null) format = "yyyy.MM.dd HH:mm:ss.SSS";
        else format = param;
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            Date d = new Date();
            df.format(d);
        } catch (Exception e) {
            format = "yyyy.MM.dd HH:mm:ss.SSS";
        }
    }
    

    @Override
    protected String format(LogRecord record) {
        if(record == null) return null;
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date d = new Date(record.getMillis());
        return df.format(d);
    }
    
}
