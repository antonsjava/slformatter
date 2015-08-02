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
public class ThreadAppender extends AbstractAppender {

    @Override
    protected String format(LogRecord record) {
        return Thread.currentThread().getName();
    }
    
}
