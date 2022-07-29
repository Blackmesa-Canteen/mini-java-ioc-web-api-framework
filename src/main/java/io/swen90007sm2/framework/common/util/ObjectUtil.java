package io.swen90007sm2.framework.common.util;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

/**
 * util for object creation/convertion
 *
 * @author xiaotian,
 * @author shuang.kou:https://github.com/Snailclimb/jsoncat
 */
public class ObjectUtil {

    /**
     * convert string to object with spefic type，
     * used to convert incoming param to the corresponding type declared in handler method param
     *
     */
    public static Object convertString2Object(String srcText, Class<?> targetClass) {
        PropertyEditor editor = PropertyEditorManager.findEditor(targetClass);
        editor.setAsText(srcText);
        return editor.getValue();
    }
}
