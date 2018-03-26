package com.xstore.qa;

import org.testng.annotations.Test;

public class ParallelDemoTest extends UserBaseTest {

    @Test
    public void test1() throws Exception {

        System.out.println(getDriver().getContext());
    }
}
