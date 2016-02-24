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
package org.talend.component.ui.wizard.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.talend.component.core.constants.IComponentConstants;
import org.talend.component.core.model.GenericElementParameter;
import org.talend.component.core.utils.ComponentsUtils;
import org.talend.component.ui.model.genericMetadata.GenericMetadataPackage;
import org.talend.component.ui.wizard.internal.IGenericWizardInternalService;
import org.talend.component.ui.wizard.internal.service.GenericWizardInternalService;
import org.talend.component.ui.wizard.persistence.SchemaUtils;
import org.talend.component.ui.wizard.ui.DynamicComposite;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.daikon.properties.presentation.Form;
import org.talend.components.api.wizard.ComponentWizardDefinition;
import org.talend.components.api.wizard.WizardImageType;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.process.EComponentCategory;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.INode;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.runtime.services.IGenericWizardService;
import org.talend.designer.core.model.components.ElementParameter;
import org.talend.repository.model.IRepositoryNode.ENodeType;
import org.talend.repository.model.RepositoryNode;

/**
 * created by ycbai on 2015年9月9日 Detailled comment
 *
 */
public class GenericWizardService implements IGenericWizardService {

    private IGenericWizardInternalService internalService = null;

    public GenericWizardService() {
        internalService = new GenericWizardInternalService();
    }

    @Override
    public List<RepositoryNode> createNodesFromComponentService(RepositoryNode curParentNode) {
        List<RepositoryNode> repNodes = new ArrayList<>();
        Set<ComponentWizardDefinition> wizardDefinitions = internalService.getComponentService().getTopLevelComponentWizards();
        for (ComponentWizardDefinition wizardDefinition : wizardDefinitions) {
            String name = wizardDefinition.getName();
            String displayName = wizardDefinition.getDisplayName();
            String folder = "metadata/" + name; //$NON-NLS-1$  //TODO: maybe need to retrieve it from component service?
            int ordinal = 100;
            ERepositoryObjectType repositoryType = internalService.createRepositoryType(name, displayName, name, folder, ordinal);
            if (curParentNode != null) {
                repNodes.add(internalService.createRepositoryNode(curParentNode, wizardDefinition.getDisplayName(),
                        repositoryType, ENodeType.SYSTEM_FOLDER));
            }
        }
        return repNodes;
    }

    @Override
    public List<String> getGenericTypeNames() {
        List<String> typeNames = new ArrayList<>();
        Set<ComponentWizardDefinition> wizardDefinitions = internalService.getComponentService().getTopLevelComponentWizards();
        for (ComponentWizardDefinition wizardDefinition : wizardDefinitions) {
            typeNames.add(wizardDefinition.getName());
        }
        return typeNames;
    }

    @Override
    public boolean isGenericType(ERepositoryObjectType repObjType) {
        if (repObjType == null) {
            return false;
        }
        List<String> genericTypeNames = getGenericTypeNames();
        if (genericTypeNames != null && genericTypeNames.contains(repObjType.getType())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isGenericItem(Item item) {
        return item != null && item.eClass() == GenericMetadataPackage.Literals.GENERIC_CONNECTION_ITEM;
    }

    @Override
    public boolean isGenericConnection(Connection connection) {
        return connection != null && connection.eClass() == GenericMetadataPackage.Literals.GENERIC_CONNECTION;
    }

    @Override
    public Image getNodeImage(String typeName) {
        InputStream imageStream = internalService.getComponentService().getWizardPngImage(typeName,
                WizardImageType.TREE_ICON_16X16);
        if (imageStream == null) {
            return null;
        }
        // node image
        ImageData id = new ImageData(imageStream);
        Image image = new Image(null, id);
        return image;
    }

    @Override
    public Image getWiardImage(String typeName) {
        InputStream imageStream = internalService.getComponentService().getWizardPngImage(typeName,
                WizardImageType.WIZARD_BANNER_75X66);
        ImageData id = new ImageData(imageStream);
        Image image = new Image(null, id);
        return image;
    }

    @Override
    public List<MetadataTable> getMetadataTables(Connection connection) {
        List<MetadataTable> metadataTables = new ArrayList<>();
        if (connection != null) {
            return SchemaUtils.getMetadataTables(connection);
        }
        return metadataTables;
    }

    @Override
    public Composite creatDynamicComposite(Composite composite, Element element, EComponentCategory sectionCategory,
            boolean isCompactView) {
        DynamicComposite dynamicComposite = null;
        if (element != null && element instanceof INode) {
            INode node = (INode) element;
            ComponentProperties props = null;
            if (node.getComponentProperties() == null) {
                props = ComponentsUtils.getComponentProperties(node.getComponent().getName());
            } else {
                props = node.getComponentProperties();
            }
            if (props != null) {
                Form form = props.getForm(EComponentCategory.ADVANCED.equals(sectionCategory) ? IComponentConstants.FORM_ADVANCED
                        : IComponentConstants.FORM_MAIN);
                dynamicComposite = new DynamicComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.NO_FOCUS, sectionCategory,
                        element, isCompactView, composite.getBackground(), form);
                List<ElementParameter> elementParameters = (List<ElementParameter>) node.getElementParameters();
                for (ElementParameter parameter : elementParameters) {
                    if (parameter instanceof GenericElementParameter) {
                        GenericElementParameter genericElementParameter = (GenericElementParameter) parameter;
                        // genericElementParameter.callBefore();
                        genericElementParameter.addPropertyChangeListener(dynamicComposite);
                    }
                }
            }
        }
        return dynamicComposite;
    }

}
