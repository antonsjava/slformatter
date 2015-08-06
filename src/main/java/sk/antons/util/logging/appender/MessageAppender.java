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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.LogRecord;

/**
 * Appends record message. (record.getMessage())
 * If throws is defined, it is also appended.
 * @author antons
 */
public class MessageAppender extends AbstractAppender {

    @Override
    protected String format(LogRecord record) {
        if(record == null) return null;
        if(record.getThrown() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(record.getMessage());
    		sb.append("\n   ").append(record.getThrown()).append(' ');
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                record.getThrown().printStackTrace(pw);
                pw.close();
                sb.append(sw.toString());
            } catch(Exception ex) { }

		    sb.append("\n");
            return sb.toString();
        } else {
            return record.getMessage();
        }
    }
    
}
