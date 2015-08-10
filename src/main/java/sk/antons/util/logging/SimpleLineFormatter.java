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

package sk.antons.util.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Formatter;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import sk.antons.util.logging.appender.AbstractAppender;
import sk.antons.util.logging.appender.AppenderFactory;

/**
 * Simple line formatter for Java logging API. The formatter 
 * pattern string uses following placeholders. 
 * <ul>
 *  <li>${date} - current date in form 'yyyy.MM.dd'</li>
 *  <li>${time} - current time in form 'HH:mm:ss.SSS'</li>
 *  <li>${ftime} - current time in form specified in parameter</li>
 *  <li>${name} - name of the logger</li>
 *  <li>${sname} - simple name of the logger all words (separated by dot) 
 *  except last one are shorten to first letter only.  </li>
 *  <li>${level} - name of the level, where log record is logged</li>
 *  <li>${thread} - name of the current thread</li>
 *  <li>${message} - logged message</li>
 * </ul>
 *
 * following placeholder are useful only for debug purposes
 *
 * <ul>
 *  <li>${class} - name of the class where log record was created (do not 
 *  use them - slow)</li>
 *  <li>${sclass} - simple name of the class where log record was created 
 *  (do not use them - slow)</li>
 *  <li>${method} - method name where log record was created (do not use 
 *  them - slow)</li>
 * </ul>
 *
 * All placeholders can be formated with max and min value. ftime 
 * placeholder has also parameter. 
 *
 * <ul>
 *  <li>'${xxx}' - will be replaced as is
 *  <li>'${xxx:num1}' - in this form there is defined maximum length
 *  <li>'${xxx:num1:num2}' - in this form there is efined maximum length and minimum length
 *  <li>'${xxx:num1:num2:param}' - in this form there is max. min length  defined and also an parameter for placeholder
 * </ul>
 *
 * The logger property 'sk.antons.util.logging.SimpleLineFormatter.pattern' is used 
 * for property file configuration. 
 *
 * @see <a href="https://github.com/antonsjava/slformatter">https://github.com/antonsjava/slformatter</a>
 * @author antons
 */
public class SimpleLineFormatter extends Formatter {
    
    private List<AbstractAppender> appenders = null;

    /**
     * Default formatter. The pattern is defined in proeprty sk.antons.util.logging.SimpleLineFormatter.pattern.
     * If the property is not defined the pattern '"${date} ${time} ${level:3:-3} ${sname:-30:-30}: ${message}'
     * is used
     */
    public SimpleLineFormatter() {
		super();
        LogManager manager = LogManager.getLogManager();
        String pattern = manager.getProperty("sk.antons.util.logging.SimpleLineFormatter.pattern");
	    if(pattern == null) pattern = "${date} ${time} ${level:3:-3} ${sname:-30:-30}: ${message} ";
        appenders = AppenderFactory.pattern(pattern);
    }

    /**
     * Defined formatter. The pattern is defined in parameter.
     * If parameter is null the pattern '"${date} ${time} ${level:3:-3} ${sname:-30:-30}: ${message}'
     * is used
     * @param pattern - pattern used for formatting log message.
     */
    public SimpleLineFormatter(String pattern) {
		super();
        LogManager manager = LogManager.getLogManager();
	    if(pattern == null) pattern = "${date} ${time} ${level:3:-3} ${sname:-30:-30}: ${message} ";
        appenders = AppenderFactory.pattern(pattern);
    }

    @Override
	public String format(LogRecord record) {
		
		StringBuilder sb = new StringBuilder();
        for(AbstractAppender appender : appenders) {
            appender.append(sb, record);
        }

        if(record.getThrown() != null) {
    		sb.append('\n').append(record.getThrown()).append('\n');
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                record.getThrown().printStackTrace(pw);
                pw.close();
                sb.append(sw.toString());
            } catch(Exception ex) { }
        }

		sb.append("\n");

        return sb.toString();
	}

}
