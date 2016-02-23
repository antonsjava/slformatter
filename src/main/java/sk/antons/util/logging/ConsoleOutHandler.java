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

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

/**
 * Simple console handler, which use System.out for handling messages.
 * Default handler level is INFO and default formatter is SimpleFormatter.
 * Calls flush for every message.
 * @author antons
 */
public class ConsoleOutHandler extends StreamHandler {

    public ConsoleOutHandler() {
        setLevel(Level.INFO);
        //setFilter(null);
        setFormatter(new SimpleFormatter());
        //setEncoding(null);
        setOutputStream(System.out);
    }

    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }

    @Override
    public void close() {
        flush();
    }
}
