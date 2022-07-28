package io.swen90007sm2.framework.common.util;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

/**
 * util for object creation/convertion
 *
 * @author xiaotian
 */
public class ObjectUtil {

    /**
     * convert string to object with spefic type
     *
     * @param srcText string to be converted
     * @param targetClass target pbkectr class
     */
    public static Object convertString2Object(String srcText, Class<?> targetClass) {
        PropertyEditor editor = PropertyEditorManager.findEditor(targetClass);
        editor.setAsText(srcText);
        return editor.getValue();
    }
}
