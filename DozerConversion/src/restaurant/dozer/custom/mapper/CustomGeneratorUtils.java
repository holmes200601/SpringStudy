package restaurant.dozer.custom.mapper;

/**
 * Copyright 2005-2013 Dozer Project
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
 * limitations under the License.
 */

import org.dozer.classmap.ClassMap;
import org.dozer.classmap.Configuration;
import org.dozer.classmap.RelationshipType;
import org.dozer.fieldmap.DozerField;
import org.dozer.fieldmap.FieldMap;
import org.dozer.fieldmap.GenericFieldMap;
import org.dozer.util.MappingUtils;

/**
 * @author Dmitry Spikhalskiy
 */
public final class CustomGeneratorUtils {
  private static final String CLASS = "class";
  private static final String CALLBACK = "callback";
  private static final String CALLBACKS = "callbacks";

  private CustomGeneratorUtils() {}

  public static boolean shouldIgnoreField(String fieldName, Class<?> srcType, Class<?> destType) {
    if (CLASS.equals(fieldName)) {
      return true;
    }
    if ((CALLBACK.equals(fieldName) || CALLBACKS.equals(fieldName))
            && (MappingUtils.isProxy(srcType) || MappingUtils.isProxy(destType))) {
      return true;
    }
    return false;
  }

  public static void addGenericMapping(ClassMap classMap, Configuration configuration, String srcName, String destName, CustomMapping mapping, Boolean isField) {
    FieldMap fieldMap = new GenericFieldMap(classMap);

    DozerField sourceField = new DozerField(srcName, null);
    DozerField destField = new DozerField(destName, null);
    
    if (isField.booleanValue()) {
    	sourceField.setAccessible(true);
        destField.setAccessible(true);
    }
    
    // Add mapping
    fieldMap.setSrcField(sourceField);
    fieldMap.setDestField(destField);
    
    // Add relationship-type
    fieldMap.setRelationshipType(RelationshipType.valueOf(mapping.relationshipType().trim()));
    
    // Add remove orphan
    fieldMap.setRemoveOrphans(mapping.removeOrphans());

    // add CopyByReferences per defect #1728159
    MappingUtils.applyGlobalCopyByReference(configuration, fieldMap, classMap);
    classMap.addFieldMapping(fieldMap);
  }
}

