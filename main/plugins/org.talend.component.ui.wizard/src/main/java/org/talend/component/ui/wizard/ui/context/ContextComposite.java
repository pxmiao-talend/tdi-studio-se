// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.component.ui.wizard.ui.context;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.swt.formtools.Form;
import org.talend.commons.ui.swt.formtools.UtilsButton;
import org.talend.component.core.constants.IContextEventProperties;
import org.talend.component.ui.model.genericMetadata.GenericConnection;
import org.talend.component.ui.wizard.handler.IContextHandler;
import org.talend.component.ui.wizard.i18n.Messages;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.daikon.properties.Properties.Deserialized;

/**
 * created by ycbai on 2015年11月20日 Detailled comment
 *
 */
public class ContextComposite extends Composite {

    private static final int HEIGHT_BUTTON_PIXEL = 30;

    private IContextHandler contextHandler;

    private UtilsButton exportContextBtn;

    private UtilsButton revertContextBtn;

    private ConnectionItem connectionItem;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public ContextComposite(Composite parent, ConnectionItem connectionItem, IContextHandler contextHandler) {
        super(parent, SWT.NONE);
        this.connectionItem = connectionItem;
        this.contextHandler = contextHandler;
        GridLayout gridLayout = new GridLayout();
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        setLayout(gridLayout);
        createControl();
    }

    private void createControl() {
        Composite exportComposite = Form.startNewGridLayout(this, 2, true, SWT.CENTER, SWT.CENTER);
        GC gc = new GC(exportComposite);

        String displayStr = Messages.getString("ContextComposite.exportAsContext"); //$NON-NLS-1$
        Point buttonSize = gc.stringExtent(displayStr);
        exportContextBtn = new UtilsButton(exportComposite, displayStr, buttonSize.x + 12, HEIGHT_BUTTON_PIXEL);
        exportContextBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                exportAsContext();
            }
        });
        displayStr = Messages.getString("ContextComposite.revertContext"); //$NON-NLS-1$
        buttonSize = gc.stringExtent(displayStr);
        revertContextBtn = new UtilsButton(exportComposite, displayStr, buttonSize.x + 12, HEIGHT_BUTTON_PIXEL);
        gc.dispose();

        revertContextBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                revertContext();
            }
        });

        RowLayout layout = (RowLayout) exportComposite.getLayout();
        layout.spacing = 20;
        exportComposite.setLayout(layout);

        refreshContextBtn();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removeAllPropertyChangeListener() {
        PropertyChangeListener[] propertyChangeListeners = this.pcs.getPropertyChangeListeners();
        for (PropertyChangeListener listener : propertyChangeListeners) {
            this.pcs.removePropertyChangeListener(listener);
        }
    }

    private final boolean isContextMode() {
        if (connectionItem != null) {
            return connectionItem.getConnection().isContextMode();
        }
        return false;
    }

    private void exportAsContext() {
        fireExportContextEvent();
        boolean exported = contextHandler.exportContext(connectionItem);
        if (exported) {
            refreshContextBtn();
            fireRefreshUIEvent();
        }
    }

    private void revertContext() {
        boolean reverted = contextHandler.revertContext(connectionItem);
        if (reverted) {
            refreshContextBtn();
            fireRefreshUIEvent();
        }
    }

    private void refreshContextBtn() {
        boolean isContextMode = isContextMode();
        exportContextBtn.setEnabled(!isContextMode);
        revertContextBtn.setEnabled(isContextMode);
    }

    private void fireExportContextEvent() {
        this.pcs.firePropertyChange(IContextEventProperties.EVENT_PROPERTY_EXPORT_CONTEXT, null, null);
    }

    private void fireRefreshUIEvent() {
        this.pcs.firePropertyChange(IContextEventProperties.EVENT_PROPERTY_REFRESH_UI, null, getComponentProperties());
    }

    private ComponentProperties getComponentProperties() {
        if (connectionItem != null) {
            GenericConnection connection = (GenericConnection) connectionItem.getConnection();
            String compPropertiesStr = connection.getCompProperties();
            if (compPropertiesStr != null) {
                Deserialized<ComponentProperties> fromSerialized = ComponentProperties.fromSerialized(compPropertiesStr,
                        ComponentProperties.class);
                if (fromSerialized != null) {
                    return fromSerialized.properties;
                }
            }
        }
        return null;
    }

    @Override
    public void dispose() {
        super.dispose();
        removeAllPropertyChangeListener();
    }

}
