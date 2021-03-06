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
package org.codehaus.griffon.runtime.pivot.controller;

import griffon.core.RunnableWithArgs;
import griffon.core.artifact.GriffonController;
import griffon.core.controller.ActionManager;
import griffon.core.threading.UIThreadManager;
import griffon.pivot.support.PivotAction;
import org.apache.pivot.wtk.Component;
import org.codehaus.griffon.runtime.core.controller.AbstractAction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 * @since 2.0.0
 */
public class PivotGriffonControllerAction extends AbstractAction {
    public static final String KEY_DESCRIPTION = "description";
    private final PivotAction toolkitAction;
    private String description;

    public PivotGriffonControllerAction(final @Nonnull UIThreadManager uiThreadManager, @Nonnull final ActionManager actionManager, @Nonnull final GriffonController controller, @Nonnull final String actionName) {
        super(actionManager, controller, actionName);
        requireNonNull(uiThreadManager, "Argument 'uiThreadManager' must not be null");

        toolkitAction = createAction(actionManager, controller, actionName);

        addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent evt) {
                uiThreadManager.runInsideUIAsync(new Runnable() {
                    public void run() {
                        handlePropertyChange(evt);
                    }
                });
            }
        });
    }

    @Nonnull
    protected PivotAction createAction(final @Nonnull ActionManager actionManager, final @Nonnull GriffonController controller, final @Nonnull String actionName) {
        return new PivotAction(new RunnableWithArgs() {
            public void run(@Nullable Object... args) {
                actionManager.invokeAction(controller, actionName, args);
            }
        });
    }

    protected void handlePropertyChange(@Nonnull PropertyChangeEvent evt) {
        if (KEY_NAME.equals(evt.getPropertyName())) {
            toolkitAction.setName((String) evt.getNewValue());
        } else if (KEY_DESCRIPTION.equals(evt.getPropertyName())) {
            toolkitAction.setDescription((String) evt.getNewValue());
        } else if (KEY_ENABLED.equals(evt.getPropertyName())) {
            toolkitAction.setEnabled((Boolean) evt.getNewValue());
        }
    }

    protected void doInitialize() {
        toolkitAction.setName(getName());
        toolkitAction.setDescription(getDescription());
        toolkitAction.setEnabled(isEnabled());
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        firePropertyChange(KEY_DESCRIPTION, this.description, this.description = description);
    }

    @Nonnull
    public Object getToolkitAction() {
        return toolkitAction;
    }

    protected void doExecute(Object... args) {
        Component component = null;
        if (args != null && args.length == 1 && args[0] instanceof Component) {
            component = (Component) args[0];
        }
        toolkitAction.perform(component);
    }
}