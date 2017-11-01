/*
 * Copyright 2017 Contributors to the Eclipse Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.cxf.client.microprofile;

import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MPClientConfigurationImpl implements Configuration{
    private final Map<String, Object> properties;
    private final Map<Feature, Boolean> features;

    public MPClientConfigurationImpl() {
        this.properties = new HashMap<>();
        this.features = new HashMap<>();
    }

    @Override
    public RuntimeType getRuntimeType() {
        return RuntimeType.CLIENT;
    }

    @Override
    public Map<String, Object> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    @Override
    public Object getProperty(String s) {
        return properties.get(s);
    }

    @Override
    public Collection<String> getPropertyNames() {
        return properties.keySet();
    }

    @Override
    public boolean isEnabled(Feature feature) {
        return features.getOrDefault(feature, false);
    }

    @Override
    public boolean isEnabled(Class<? extends Feature> aClass) {
        for(Map.Entry<Feature, Boolean> registeredFeature : features.entrySet()) {
            if(registeredFeature.getKey().getClass().equals(aClass)) {
                return registeredFeature.getValue();
            }
        }
        return false;
    }

    @Override
    public boolean isRegistered(Object o) {
        return false;
    }

    @Override
    public boolean isRegistered(Class<?> aClass) {
        return false;
    }

    @Override
    public Map<Class<?>, Integer> getContracts(Class<?> aClass) {
        return null;
    }

    @Override
    public Set<Class<?>> getClasses() {
        return null;
    }

    @Override
    public Set<Object> getInstances() {
        return null;
    }
}
