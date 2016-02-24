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
package org.talend.component.ui.wizard.ui.dnd;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.talend.component.core.utils.ComponentsUtils;
import org.talend.component.ui.model.genericMetadata.GenericConnection;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.utils.AbstractDragAndDropServiceHandler;

/**
 * created by hcyi on Feb 22, 2016 Detailled comment
 *
 */
public class GenericDragAndDropHandlerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testIsGenericRepositoryValue() {
        Connection connection = mock(Connection.class);
        AbstractDragAndDropServiceHandler abstractDragAndDropServiceHandler = mock(AbstractDragAndDropServiceHandler.class);
        boolean isGenericRepositoryValue = abstractDragAndDropServiceHandler.isGenericRepositoryValue(connection, "paramName1");//$NON-NLS-1$
        assertEquals(false, isGenericRepositoryValue);

        connection = mock(GenericConnection.class);
        GenericDragAndDropHandler genericDragAndDropHandler = mock(GenericDragAndDropHandler.class);
        when(genericDragAndDropHandler.canHandle(connection)).thenReturn(true);
        isGenericRepositoryValue = genericDragAndDropHandler.isGenericRepositoryValue(null, "paramName2");//$NON-NLS-1$
        assertEquals(false, isGenericRepositoryValue);

        PowerMockito.mockStatic(ComponentsUtils.class);
        ComponentProperties mockComponentProperties = mock(ComponentProperties.class);
        // when(ComponentsUtils.getComponentPropertiesFromSerialized(null)).thenReturn(mockComponentProperties);
        // List<Property> propertyValues = new ArrayList<Property>();
        // Property element = ComponentPropertyFactory.newReturnsProperty();
        // propertyValues.add(element);
        // when(ComponentsUtils.getAllValuedProperties(mockComponentProperties)).thenReturn(propertyValues);
        //        isGenericRepositoryValue = genericDragAndDropHandler.isGenericRepositoryValue(connection, "QueryMode");//$NON-NLS-1$
        // assertEquals(true, isGenericRepositoryValue);
    }
}
