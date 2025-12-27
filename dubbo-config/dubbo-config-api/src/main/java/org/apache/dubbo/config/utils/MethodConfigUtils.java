/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.config.utils;

import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.MethodConfig;
import org.apache.dubbo.config.annotation.Method;

import java.util.HashMap;
import java.util.Map;

public class MethodConfigUtils {

    public static MethodConfig createFromAnnotation(Method annotation, String methodName) {
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName(methodName);

        if (annotation.timeout() != -1) {
            methodConfig.setTimeout(annotation.timeout());
        }
        if (annotation.retries() != -1) {
            methodConfig.setRetries(annotation.retries());
        }
        if (annotation.actives() != -1) {
            methodConfig.setActives(annotation.actives());
        }
        if (annotation.executes() != -1) {
            methodConfig.setExecutes(annotation.executes());
        }

        if (StringUtils.hasText(annotation.loadbalance())) {
            methodConfig.setLoadbalance(annotation.loadbalance());
        }
        if (StringUtils.hasText(annotation.merger())) {
            methodConfig.setMerger(annotation.merger());
        }
        if (StringUtils.hasText(annotation.cache())) {
            methodConfig.setCache(annotation.cache());
        }
        if (StringUtils.hasText(annotation.validation())) {
            methodConfig.setValidation(annotation.validation());
        }
        if (StringUtils.hasText(annotation.oninvoke())) {
            methodConfig.setOninvoke(annotation.oninvoke());
        }
        if (StringUtils.hasText(annotation.onreturn())) {
            methodConfig.setOnreturn(annotation.onreturn());
        }
        if (StringUtils.hasText(annotation.onthrow())) {
            methodConfig.setOnthrow(annotation.onthrow());
        }

        if (annotation.async()) {
            methodConfig.setAsync(true);
        }
        if (!annotation.sent()) {
            methodConfig.setSent(false);
        }
        if (annotation.sticky()) {
            methodConfig.setSticky(true);
        }
        if (!annotation.isReturn()) {
            methodConfig.setReturn(false);
        }
        if (annotation.deprecated()) {
            methodConfig.setDeprecated(true);
        }

        if (annotation.parameters().length > 0) {
            methodConfig.setParameters(convertParameters(annotation.parameters()));
        }

        return methodConfig;
    }

    private static Map<String, String> convertParameters(String[] parameters) {
        if (parameters == null || parameters.length == 0) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < parameters.length; i += 2) {
            if (i + 1 < parameters.length) {
                map.put(parameters[i], parameters[i + 1]);
            }
        }
        return map;
    }
}
