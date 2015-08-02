/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.antons.util.logging.appender;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.LogRecord;

/**
 *
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
