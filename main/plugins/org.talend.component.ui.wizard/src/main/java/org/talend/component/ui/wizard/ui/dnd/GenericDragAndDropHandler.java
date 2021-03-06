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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.component.core.constants.IComponentConstants;
import org.talend.component.core.model.Component;
import org.talend.component.core.model.GenericElementParameter;
import org.talend.component.core.utils.ComponentsUtils;
import org.talend.component.core.utils.SchemaUtils;
import org.talend.component.ui.model.genericMetadata.GenericConnection;
import org.talend.component.ui.model.genericMetadata.GenericConnectionItem;
import org.talend.component.ui.model.genericMetadata.SubContainer;
import org.talend.components.api.component.ComponentDefinition;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.components.EComponentType;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.components.IComponentsService;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataToolHelper;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.model.repository.RepositoryViewObject;
import org.talend.core.model.utils.AbstractDragAndDropServiceHandler;
import org.talend.core.model.utils.IComponentName;
import org.talend.core.repository.RepositoryComponentSetting;
import org.talend.core.repository.model.repositoryObject.MetadataColumnRepositoryObject;
import org.talend.core.repository.model.repositoryObject.MetadataTableRepositoryObject;
import org.talend.daikon.properties.Property;
import org.talend.repository.model.RepositoryNode;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * 
 * created by hcyi on Oct 26, 2015 Detailled comment
 *
 */
public class GenericDragAndDropHandler extends AbstractDragAndDropServiceHandler {

    @Override
    public boolean canHandle(Connection connection) {
        return connection instanceof GenericConnection;
    }

    @Override
    public Object getComponentValue(Connection connection, String value, IMetadataTable table, String targetComponent) {
        if (value != null && canHandle(connection)) {
            return getGenericRepositoryValue((GenericConnection) connection, value, table);
        }
        return null;
    }

    private Object getGenericRepositoryValue(GenericConnection connection, String value, IMetadataTable table) {
        if (connection == null) {
            return null;
        }
        ComponentProperties componentProperties = ComponentsUtils.getComponentPropertiesFromSerialized(connection
                .getCompProperties());
        String paramName = ComponentsUtils.getPropertyName(value);
        if (value != null && value.startsWith(componentProperties.getName())) {
            if (value.indexOf(IComponentConstants.EXP_SEPARATOR) != -1) {
                value = value.substring(componentProperties.getName().length() + 1, value.length());
            }
        }

        Object object = ComponentsUtils.getGenericPropertyValue(componentProperties, value);
        if (object != null && object instanceof String) {
            return getRepositoryValueOfStringType(connection, StringUtils.trimToNull(object.toString()));
        }
        return ComponentsUtils.getGenericPropertyValue(componentProperties, value);

    }

    @Override
    public boolean isGenericRepositoryValue(Connection connection, String paramName) {
        if (paramName != null && canHandle(connection)) {
            return isGenericRepositoryValue((GenericConnection) connection, paramName);
        }
        return false;
    }

    public boolean isGenericRepositoryValue(GenericConnection connection, String paramName) {
        if (connection == null) {
            return false;
        }
        ComponentProperties componentProperties = ComponentsUtils.getComponentPropertiesFromSerialized(connection
                .getCompProperties());
        List<Property> propertyValues = ComponentsUtils.getAllValuedProperties(componentProperties);
        for (Property property : propertyValues) {
            paramName = ComponentsUtils.getPropertyName(paramName);
            if (property.getName().equals(paramName)) {
                return property.getTaggedValue(IComponentConstants.REPOSITORY_VALUE) != null;
            }
        }
        for (ModelElement modelElement : connection.getOwnedElement()) {
            if (modelElement instanceof SubContainer) {
                SubContainer subContainer = (SubContainer) modelElement;
                ComponentProperties subComponentProperties = ComponentsUtils.getComponentPropertiesFromSerialized(subContainer
                        .getCompProperties());
                List<Property> subPropertyValues = ComponentsUtils.getAllValuedProperties(subComponentProperties);
                for (Property subProperty : subPropertyValues) {
                    paramName = ComponentsUtils.getPropertyName(paramName);
                    if (subProperty.getName().equals(paramName)) {
                        return subProperty.getTaggedValue(IComponentConstants.REPOSITORY_VALUE) != null;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<IComponent> filterNeededComponents(Item item, RepositoryNode seletetedNode, ERepositoryObjectType type) {
        // TUP-4151
        List<IComponent> neededComponents = new ArrayList<>();
        if (!(item instanceof GenericConnectionItem)) {
            return neededComponents;
        }
        IComponentsService service = (IComponentsService) GlobalServiceRegister.getDefault().getService(IComponentsService.class);
        Set<IComponent> components = service.getComponentsFactory().getComponents();
        for (IComponent component : components) {
            if (EComponentType.GENERIC.equals(component.getComponentType())) {
                if (!neededComponents.contains(component) && isValid(seletetedNode, component)) {
                    neededComponents.add(component);
                }
            }
        }
        return neededComponents;
    }

    private boolean isValid(RepositoryNode seletetedNode, IComponent component) {
        // TUP-4151
        IRepositoryViewObject object = seletetedNode.getObject();
        if (component == null || object == null) {
            return false;
        }
        ComponentProperties currentComponentProperties = null;
        if (object instanceof RepositoryViewObject) {
            RepositoryViewObject repositoryViewObj = (RepositoryViewObject) object;
            Connection connection = ((ConnectionItem) repositoryViewObj.getProperty().getItem()).getConnection();
            if (canHandle(connection)) {
                GenericConnection genericConnection = (GenericConnection) connection;
                currentComponentProperties = ComponentsUtils.getComponentPropertiesFromSerialized(genericConnection
                        .getCompProperties());
            }
        } else if (object instanceof MetadataTableRepositoryObject) {
            MetadataTableRepositoryObject metaTableRepObj = (MetadataTableRepositoryObject) object;
            currentComponentProperties = SchemaUtils.getCurrentComponentProperties(metaTableRepObj);
        } else if (object instanceof MetadataColumnRepositoryObject) {
            MetadataColumnRepositoryObject metaColumnRepObj = (MetadataColumnRepositoryObject) object;
            ModelElement element = metaColumnRepObj.getTdColumn();
            if (element != null && element.eContainer() instanceof MetadataTable) {
                MetadataTable metadataTable = (MetadataTable) element.eContainer();
                IMetadataTable newTable = MetadataToolHelper.convert(metadataTable);
                currentComponentProperties = SchemaUtils.getCurrentComponentProperties(newTable);
            }
        }
        //
        if (currentComponentProperties != null) {
            try {
                List<ComponentDefinition> possibleComponents = ComponentsUtils.getComponentService().getPossibleComponents(
                        currentComponentProperties);
                if (component instanceof Component) {
                    ComponentDefinition componentDefinition = ((Component) component).getComponentDefinition();
                    return possibleComponents.contains(componentDefinition);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public IComponentName getCorrespondingComponentName(Item item, ERepositoryObjectType type) {
        RepositoryComponentSetting setting = null;
        List<Class<Item>> list = new ArrayList<Class<Item>>();
        if (item instanceof GenericConnectionItem) {
            setting = new RepositoryComponentSetting();
            setting.setWithSchema(true);
            Class clazz = null;
            try {
                clazz = Class.forName(GenericConnectionItem.class.getName());
            } catch (ClassNotFoundException e) {
                ExceptionHandler.process(e);
            }
            list.add(clazz);
            setting.setClasses(list.toArray(new Class[0]));
        }
        return setting;
    }

    private void setGenericRepositoryValue(GenericConnection connection, INode node, IElementParameter param) {
        if (connection != null && param != null && param instanceof GenericElementParameter) {
            if (connection.getCompProperties() == null) {
                GenericElementParameter genericParam = (GenericElementParameter) param;
                connection.setCompProperties(genericParam.getComponentProperties().toSerialized());
            }
        }
    }

    @Override
    public ERepositoryObjectType getType(String repositoryType) {
        return null;
    }

    @Override
    public void setComponentValue(Connection connection, INode node, IElementParameter param) {
        if (node != null && canHandle(connection)) {
            setGenericRepositoryValue((GenericConnection) connection, node, param);
        }
    }

    @Override
    public void handleTableRelevantParameters(Connection connection, IElement ele, IMetadataTable metadataTable) {

    }
}
