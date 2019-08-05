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

import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class DatasetConversionController {

    @PostMapping(value = "/rdf/converter", consumes = "application/n-quads",
    produces = {"application/ld+json", "application/ld+json;charset=UTF-8"})
    public @ResponseBody Dataset convertDataset(@RequestBody Dataset dataset) {
        return dataset;
    }
}
