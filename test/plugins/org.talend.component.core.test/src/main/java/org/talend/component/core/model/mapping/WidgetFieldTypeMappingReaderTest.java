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
package org.talend.component.core.model.mapping;

import junit.framework.Assert;

import org.junit.Test;
import org.talend.daikon.NamedThing;
import org.talend.daikon.SimpleNamedThing;

/**
 * created by hcyi on Feb 16, 2016 Detailled comment
 *
 */
public class WidgetFieldTypeMappingReaderTest {

    @Test
    public void testGetFieldType() {
        NamedThing nameAndLabel = new SimpleNamedThing("testName", "testLabel");//$NON-NLS-1$//$NON-NLS-2$
        Assert.assertEquals("testName", nameAndLabel.getName());//$NON-NLS-1$
        Assert.assertEquals("testLabel", nameAndLabel.getDisplayName());//$NON-NLS-1$
        Assert.assertNull(nameAndLabel.getTitle());
        WidgetFieldTypeMappingReader mappingReader = WidgetFieldTypeMappingReader.getInstance();
        String fieldType = mappingReader.getFieldType("DEFAULT", nameAndLabel, "STRING");//$NON-NLS-1$//$NON-NLS-2$
        Assert.assertEquals("TEXT", fieldType);//$NON-NLS-1$
        fieldType = mappingReader.getFieldType("DEFAULT", nameAndLabel, null);//$NON-NLS-1$
        Assert.assertEquals("LABEL", fieldType);//$NON-NLS-1$
        fieldType = mappingReader.getFieldType("HIDDEN_TEXT", nameAndLabel, "HIDDEN_TEXT");//$NON-NLS-1$//$NON-NLS-2$
        Assert.assertEquals("PASSWORD", fieldType);//$NON-NLS-1$
    }
}
