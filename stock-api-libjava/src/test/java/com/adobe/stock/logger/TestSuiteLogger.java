/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.logger;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestSuiteLogger implements ISuiteListener {

    private static final String ANSI_BOLD = "\033[1m";
    private static final String ANSI_RESET = "\033[0m";

    @Override
    public void onStart(ISuite suite) {
        log("Test Suite: " + suite.getName() + "\n");
    }

    @Override
    public void onFinish(ISuite suite) {
        log("");
    }

    private void log(String string) {
        System.out.println(ANSI_BOLD + string + ANSI_RESET);
    }

}
