package com.buddybuild.bbandroidsampletests;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final Logger log = Logger.getLogger(ExampleInstrumentedTest.class.getName());

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
        MyFormatter formatter = new MyFormatter();
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
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.buddybuild.bbandroidsampletests", appContext.getPackageName());
    }
}
