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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(DatasetConversionControllerTest.class)
public class DatasetConversionControllerTest {

    private static final MediaType JSON_LD_MEDIA_TYPE = MediaType.valueOf("application/ld+json;charset=UTF-8");
    private static final MediaType NQUADS_MEDIA_TYPE = MediaType.valueOf("application/n-quads");
    @Autowired
    MockMvc mockMvc;

    @Test
    public void canary() {
        Assert.assertNotNull(mockMvc);
    }

    @Test
    public void nQuadsCanBeConvertedToJsonLd() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/rdf/converter")
                .contentType(NQUADS_MEDIA_TYPE)
                .accept(JSON_LD_MEDIA_TYPE)
                .content(SIMPLE_NQUADS))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(JSON_LD_MEDIA_TYPE));
    }
    private static String getExpectedSimpleJsonLd() {
    return "";
    }
    private static final String SIMPLE_NQUADS = "<http://one.example/subject1> <http://one.example/predicate1> <http://one.example/object1> <http://example.org/graph3> .";


}