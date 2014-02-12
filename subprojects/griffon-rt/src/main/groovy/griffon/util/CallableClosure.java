/*
 * Copyright 2009-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package griffon.util;

import groovy.lang.Closure;

import java.util.concurrent.Callable;

/**
 * Helper class that executes a Closure as Callable.
 *
 * @author Andres Almiray
 */
public class CallableClosure<V> implements Callable<V> {
    private final Closure closure;

    public CallableClosure(Closure closure) {
        this.closure = closure;
    }

    public V call() {
        return (V) closure.call();
    }
}
