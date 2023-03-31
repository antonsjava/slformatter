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

import java.util.logging.LogRecord;

/**
 * Abstract parent for any placeholder implementation.
 *
 * @author antons
 */
public abstract class AbstractAppender {

    protected int minLength = 0;
    protected boolean minLeft = true;
    protected int maxLength = 0;
    protected boolean maxLeft = true;
    protected String param = null;


    public AbstractAppender() {
    }

    /**
     * Helper method. It is called after whole configuration
     * and before usage;
     */
    public void consolidate() {
        if(minLength < 0) {
            minLength = Math.abs(minLength);
            minLeft = true;
        } else {
            minLeft = false;
        }
        if(maxLength < 0) {
            maxLength = Math.abs(maxLength);
            maxLeft = true;
        } else {
            maxLeft = false;
        }
    }

    /**
     * Generate string to be replaced by this [placeholder.
     * #param record - log recort with data for placeholder string generation.
     */
    protected abstract String format(LogRecord record);

    /**
     * Appends this placeholder to provided StringBuilder.
     *
     * @param sb - builder where polaceholder string is to be appended.
     * @param record - input data for generation of replacement string.
     */
    public void append(StringBuilder sb, LogRecord record) {
        String text = format(record);
        append(sb, text);
    }

    protected void append(StringBuilder sb, String text) {
        if(text == null) text = "null";
        if(maxLength > 0) {
            if(text.length() > maxLength) {
                if(!maxLeft) text = text.substring(0, maxLength);
                else text = text.substring(text.length() - maxLength);
            }
        }

        if(minLength == 0) {
            sb.append(text);
        } else {
            if(text.length() < minLength) {
                int length = minLength - text.length();
                if(minLeft) {
                    for(int i = 0; i < length; i++) sb.append(' ');
                    sb.append(text);
                } else {
                    sb.append(text);
                    for(int i = 0; i < length; i++) sb.append(' ');
                }
            } else {
                sb.append(text);
            }
        }
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void setParam(String param) {
        this.param = param;
    }


}
