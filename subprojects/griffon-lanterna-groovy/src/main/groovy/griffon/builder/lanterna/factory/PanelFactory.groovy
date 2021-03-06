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
package griffon.builder.lanterna.factory

import com.googlecode.lanterna.gui.component.Panel

/**
 * @author Andres Almiray
 */
class PanelFactory extends ComponentFactory {
    PanelFactory() {
        super(Panel)
    }

    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        def orientation = attributes.remove('orientation') ?: Panel.Orientation.HORISONTAL
        if (orientation instanceof CharSequence) {
            orientation = orientation.toString().toLowerCase()
            switch (orientation) {
                case 'vertical':
                    orientation = Panel.Orientation.VERTICAL
                    break
                case 'horizontal':
                case 'horisontal':
                default:
                    orientation = Panel.Orientation.HORISONTAL
            }
        } else if (!(orientation instanceof Panel.Orientation)) {
            throw new IllegalArgumentException("In $name the value of orientation: must be either 'horisontal' or 'vertical'.")
        }

        value instanceof CharSequence ? new Panel(value.toString(), orientation) : new Panel(orientation)
    }
}
