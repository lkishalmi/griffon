/*
 * Copyright 2008-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package griffon.pivot.support.adapters;

import griffon.core.CallableWithArgs;

/**
 * @author Andres Almiray
 * @since 2.0.0
 */
public class WindowClassAdapter implements GriffonPivotAdapter, org.apache.pivot.wtk.WindowClassListener {
    private CallableWithArgs<Void> activeWindowChanged;

    public CallableWithArgs<Void> getActiveWindowChanged() {
        return this.activeWindowChanged;
    }


    public void setActiveWindowChanged(CallableWithArgs<Void> activeWindowChanged) {
        this.activeWindowChanged = activeWindowChanged;
    }


    public void activeWindowChanged(org.apache.pivot.wtk.Window arg0) {
        if (activeWindowChanged != null) {
            activeWindowChanged.call(arg0);
        }
    }

}
