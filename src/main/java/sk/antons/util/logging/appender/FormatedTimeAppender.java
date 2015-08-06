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
import java.util.Date;
import java.util.logging.LogRecord;

/**
 * Appends record time in specified form. (record.getMillis()) 
 * Format is specified in parameter.
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
