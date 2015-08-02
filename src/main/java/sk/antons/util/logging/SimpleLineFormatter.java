package sk.antons.util.logging;

import java.util.List;
import java.util.logging.Formatter;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import sk.antons.util.logging.appender.AbstractAppender;
import sk.antons.util.logging.appender.AppenderFactory;

/**
 *
 * @author antons
 */
public class SimpleLineFormatter extends Formatter {
    
    List<AbstractAppender> appenders = null;
    public SimpleLineFormatter() {
		super();
        LogManager manager = LogManager.getLogManager();
        String pattern = manager.getProperty("sk.antons.util.logging.SimpleLineFormatter.pattern");
	    if(pattern == null) pattern = "${date} ${time} ${level:3:-3} ${sname:-30:-30}: ${message} ";
        appenders = AppenderFactory.pattern(pattern);
    }

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
		sb.append("\n");

        return sb.toString();
	}

}
