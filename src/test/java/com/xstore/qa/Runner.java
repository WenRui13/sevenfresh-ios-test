package com.xstore.qa;

import com.appium.manager.ParallelThread;
import com.report.factory.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    @Test
    public static void testApp() throws Exception {
        ExtentManager.setSystemInfoInReport("version","1.0.0");
        List<String> tests = new ArrayList<>();
        tests.add("ParallelDemoTest");
//        tests.add("DemoTest");

        ParallelThread parallelThread = new ParallelThread();
        boolean hasFailures = parallelThread.runner("com.xstore.qa",tests);
        Assert.assertFalse(hasFailures, "Testcases have failed in parallel execution");
    }
}
