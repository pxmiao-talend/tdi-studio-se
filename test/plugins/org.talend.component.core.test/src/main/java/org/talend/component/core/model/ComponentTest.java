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
package org.talend.component.core.model;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.talend.components.api.component.ComponentDefinition;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;

/**
 * created by hcyi on Feb 17, 2016 Detailled comment
 *
 */
public class ComponentTest {

    private Component component;

    protected Component getComponent() {
        return component;
    }

    @Before
    public void setUp() throws Exception {
        initComponent();
    }

    protected void initComponent() throws Exception {
        component = mock(Component.class);
        ComponentDefinition componentDefinition = mock(ComponentDefinition.class);
        when(component.getComponentDefinition()).thenReturn(componentDefinition);
        component.setPaletteType("DI"); //$NON-NLS-1$
        when(component.getName()).thenReturn("tSalesforce");//$NON-NLS-1$
        when(component.getLongName()).thenReturn("tSalesforce");//$NON-NLS-1$
        //
    }

    @Test
    public void testGetElementParameterValueFromComponentProperties() {
        INode iNode = mock(INode.class);
        ComponentProperties iNodeComponentProperties = mock(ComponentProperties.class);
        iNode.setComponentProperties(iNodeComponentProperties);
        IElementParameter param = mock(GenericElementParameter.class);
        Object obj = component.getElementParameterValueFromComponentProperties(iNode, param);
        Assert.assertNull(obj);
    }
}
