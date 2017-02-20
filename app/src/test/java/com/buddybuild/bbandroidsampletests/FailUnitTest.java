package com.buddybuild.bbandroidsampletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FailUnitTest {

    private static final Logger log = Logger.getLogger(FailUnitTest.class.getName());

    private static void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        if(status.equals("finished")) {
            log.info(String.format("Buddybuild %s %s, [%.3fs]",
                    testName, status, TimeUnit.NANOSECONDS.toMillis(nanos) / 1000f));
        }
        if(status.equals("Starting")) {
            log.info(String.format("Buddybuild %s %s",testName, status));
        }
    }

    class MyFormatter extends Formatter {
        public String format(LogRecord record) {

            return record.getMessage();
        }
        public String getHead(Handler h) {
            return super.getHead(h);
        }
        public String getTail(Handler h) {
            return super.getTail(h);
        }
    }

    @Before
    public void setUp() throws Exception {

        log.setUseParentHandlers(false);
        Handler[] list = log.getHandlers();
        for(Handler h: list){
            log.removeHandler(h);
        }
        FailUnitTest.MyFormatter formatter = new FailUnitTest.MyFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
        log.addHandler(handler);
    }

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void succeeded(long nanos, Description description) {
            logInfo(description, "Starting", nanos);
        }

        @Override
        protected void finished(long nanos, Description description) {
            logInfo(description, "finished", nanos);
        }
    };

    @Test
    public void assert_IsNotCorrect() throws Exception {
        assertEquals(4, 3);
    }
}