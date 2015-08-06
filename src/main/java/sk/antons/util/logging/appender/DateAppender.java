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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.LogRecord;

/**
 * Appends record date in form yyyy.MM.dd. (record.getMillis()) 
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
