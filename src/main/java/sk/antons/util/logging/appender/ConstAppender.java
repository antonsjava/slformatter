/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.antons.util.logging.appender;

import java.util.logging.LogRecord;

/**
 *
 * @author antons
 */
public class ConstAppender extends AbstractAppender {

    private String text = null;
    public ConstAppender(String text) {
        this.text = text;
    }

    @Override
    protected String format(LogRecord record) {
        return text;
    }
    
}
