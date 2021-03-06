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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Factory class for parsing pattern string.
 * @author antons
 */
public class AppenderFactory {


    /**
     * Parsess pattern string. Create list of appenders which 
     * formats logrecords to result string.
     * @param pattern
     * @return 
     */
    public static List<AbstractAppender> pattern(String pattern) {
        List<AbstractAppender> rv = new ArrayList<AbstractAppender>();
        if(pattern == null) return rv;

        int lastStartPos = 0;
        int lastEndPos = 0;
        int startPos = 0;
        int endPos = 0;
        startPos = pattern.indexOf("${", lastEndPos);
        if(startPos > -1) endPos = pattern.indexOf("}", startPos);
        while ((startPos > -1) && (endPos > -1)) {
            if(startPos > lastEndPos + 1) {
                if(lastEndPos == 0) rv.add(new ConstAppender(pattern.substring(lastEndPos, startPos)));
                else rv.add(new ConstAppender(pattern.substring(lastEndPos+1, startPos)));
            }
            String conf = pattern.substring(startPos + 2, endPos);
            String name = null;
            String max = "0";
            String min = "0";
            String param = null;
            int pos1 = conf.indexOf(":");
            if(pos1 > -1) {
                name = conf.substring(0, pos1);
                int pos2 = conf.indexOf(":", pos1+1);
                if(pos2 > -1) {
                    max = conf.substring(pos1+1, pos2);
                    int pos3 = conf.indexOf(":", pos2+1);
                    if(pos3 > -1) {
                        min = conf.substring(pos2+1, pos3);
                        param = conf.substring(pos3+1);
                    } else {
                        min = conf.substring(pos2+1);
                    }
                } else {
                    max = conf.substring(pos1+1);
                }
            } else {
                name = conf;
            }

            AbstractAppender ap = null;
            if("name".equals(name)) {
                ap = new NameAppender();
            } else if("sname".equals(name)) {
                ap = new ShortNameAppender();
            } else if("level".equals(name)) {
                ap = new LevelAppender();
            } else if("date".equals(name)) {
                ap = new DateAppender();
            } else if("time".equals(name)) {
                ap = new TimeAppender();
            } else if("ftime".equals(name)) {
                ap = new FormatedTimeAppender();
            } else if("class".equals(name)) {
                ap = new ClassAppender();
            } else if("sclass".equals(name)) {
                ap = new ShortClassAppender();
            } else if("message".equals(name)) {
                ap = new MessageAppender();
            } else if("method".equals(name)) {
                ap = new MethodAppender();
            } else if("thread".equals(name)) {
                ap = new ThreadAppender();
            } else {
                ap = new ConstAppender("${" + conf + "}");
                min = "0";
                max = "0";
            }
            ap.setMaxLength(safeInt(max));
            ap.setMinLength(safeInt(min));
            ap.setParam(param);
            ap.consolidate();
            rv.add(ap);

            
            lastStartPos = startPos;
            lastEndPos = endPos;;
            startPos = pattern.indexOf("${", lastEndPos);
            if(startPos > -1) endPos = pattern.indexOf("}", startPos);
        }
        if(pattern.length() > lastEndPos + 1) {
            rv.add(new ConstAppender(pattern.substring(lastEndPos + 1)));
        }


        
        return rv;
    }
    

    private static int safeInt(String text) {
        if(text == null) return 0;
        int rv = 0;
        try {rv = Integer.parseInt(text);} catch (NumberFormatException e) {}
        return rv;
    }

    private static Map<String, String> clNameCache = new Hashtable<String, String>();
    /**
     * Generates short class name form. It uses static cache for computed results.
     * @param className - incoming fully qualified class name (like sk.antons.util.logging.appender.AppenderFactory)
     * @return computed short form (like s.a.u.l.a.AppenderFactor)
     */
    public static String shortClassName(String className) {
        if(className == null) return null;
        String text = clNameCache.get(className);
        if(text == null) {
            text = formatClassName(className);
            clNameCache.put(className, text);
        }
        return text;   
    }
    
    private static String formatClassName(String className) {
        if(className == null) return null;
        StringBuilder sb = new StringBuilder();
        int lastPos = 0;
        int pos = className.indexOf(".");
        int append = 1;
        while(pos > -1) {
            sb.append(className.substring(lastPos, lastPos + append));
            lastPos = pos;
            pos = className.indexOf(".", pos + 1);
            append = 2;
        }
        sb.append(className.substring(lastPos));
        return sb.toString();
    }
}
