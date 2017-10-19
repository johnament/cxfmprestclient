/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.cxf.client.microprofile;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.eclipse.microprofile.rest.client.TypeSafeClientBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class CxfTypeSafeClientBuilder extends TypeSafeClientBuilder {
    private URI baseUri;
    @Override
    public TypeSafeClientBuilder baseUrl(URL url) {
        try {
            this.baseUri = Objects.requireNonNull(url).toURI();
            return this;
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to parse URL "+url,e);
        }
    }
    @Override
    public <T> T proxy(Class<T> aClass) {
        return JAXRSClientFactory.create(baseUri, aClass);
    }
}
