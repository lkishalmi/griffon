/*
 * Copyright 2007-2014 the original author or authors.
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
 */

/**
 * @author Andres Almiray
 */

package griffon.samples.swingpad

panel(id: 'content') {
    migLayout layoutConstraints: 'fill'
    scrollPane(constraints: 'grow, wrap') {
        textArea(editable: false, text: bind { model.license },
                caretPosition: bind('license', source: model, converter: {0i}))
    }
    button(hideAction, constraints: 'right')

    keyStrokeAction(component: current,
            keyStroke: 'ESCAPE',
            condition: 'in focused window',
            action: hideAction)
}
