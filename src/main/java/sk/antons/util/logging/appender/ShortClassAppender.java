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
 * Appends record class in short form. (record.getSourceClassName()) 
 * @author antons
 */
public class ShortClassAppender extends AbstractAppender {

    @Override
    protected String format(LogRecord record) {
        if(record == null) return null;
        return AppenderFactory.shortClassName(record.getSourceClassName());
    }
    
}
