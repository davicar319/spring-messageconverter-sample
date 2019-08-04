/*
 * Copyright (c) 2019 David A Carlson, Bixby's Dog House Software.
 *
 * Free to use and/or modify under the Apache License 2.0.  See the LICENSE file in the root directory
 *  of the project for more information.
 *                                  Apache License
 *                            Version 2.0, January 2004
 *                         http://www.apache.org/licenses/
 *
 */

package com.bixbysdoghouse.training.spring.springmessageconvertersample.web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(DatasetConversionController.class)
public class DatasetConversionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void canary() {
        Assert.assertNotNull(mockMvc);
    }
}