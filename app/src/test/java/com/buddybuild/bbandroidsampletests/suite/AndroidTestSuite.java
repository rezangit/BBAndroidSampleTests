package com.buddybuild.bbandroidsampletests.suite;

import com.buddybuild.bbandroidsampletests.ArithmeticUnitTest;
import com.buddybuild.bbandroidsampletests.FailUnitTest;
import com.buddybuild.bbandroidsampletests.StringUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by reza on 20/02/17.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ArithmeticUnitTest.class, FailUnitTest.class, StringUnitTest.class})
public class AndroidTestSuite {
}
