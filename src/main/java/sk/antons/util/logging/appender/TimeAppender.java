/*
 * Copyright 2015 Anton Straka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sk.antons.util.logging.appender;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.LogRecord;

/**
 * Appends record time in form HH:mm:ss.SSS. (record.getMillis()) 
 * @author antons
 */
public class TimeAppender extends AbstractAppender {
    private long starttime = 0;
    private long endtime = 0;

    private static Map<Long, String> twoCache = new Hashtable<Long, String>();
    private String two(long num) {
        String rv = twoCache.get(num);
        if(rv != null) return rv;
        rv = String.valueOf(num);
        if(num < 10) rv = "0" + rv;
        twoCache.put(num, rv);
        return rv;
    }    

    private static Map<Long, String> threeCache = new Hashtable<Long, String>();
    private String three(long num) {
        String rv = threeCache.get(num);
        if(rv != null) return rv;
        rv = String.valueOf(num);
        if(num < 10) rv = "0" + rv;
        if(num < 100) rv = "0" + rv;
        threeCache.put(num, rv);
        return rv;
    }    

    
    private synchronized String time(long millis) {
        if((starttime < millis) && (millis >= endtime)) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(millis);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            starttime = c.getTime().getTime();
            c.add(Calendar.DAY_OF_MONTH, 1);
            endtime = c.getTime().getTime();
        }
        long tm = millis - starttime;
        long ms = tm % 1000;
        tm = tm/1000;
        long s = tm % 60;
        tm = tm/60;
        long m = tm % 60;
        tm = tm/60;
        long h = tm % 24;


        StringBuilder sb = new StringBuilder();
        sb.append(two(h));
        sb.append(':');
        sb.append(two(m));
        sb.append(':');
        sb.append(two(s));
        sb.append('.');
        sb.append(three(ms));
        return sb.toString();
    }

    @Override
    protected String format(LogRecord record) {
        if(record == null) return null;
        return time(record.getMillis());
    }
    
}
