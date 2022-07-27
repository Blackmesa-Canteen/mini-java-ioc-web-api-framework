package io.swen90007sm2.core.helper;

import io.swen90007sm2.annotation.Handler;
import io.swen90007sm2.annotation.HandlesRequest;
import io.swen90007sm2.bean.Request;
import io.swen90007sm2.bean.Worker;
import io.swen90007sm2.core.util.ReflectionUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * helper for Handler level
 * @author xiaotian
 */
public class HandlerManager {

    /**
     * This map holds map: [RequestParam(Method + URL)] to [Handler method].
     * Worker object is a map of Handler class and a method
     */
    private static final Map<Request, Worker> REQUEST_WORKER_MAP = new HashMap<>();

    static {
        Set<Class<?>> handlerClassSet = ClassManager.getHandlerClassSet();
        if(!handlerClassSet.isEmpty()) {
            for (Class<?> handlerClass :handlerClassSet) {
                mapRequestWithWorkerInTheHandler(handlerClass);
            }
        }
    }

    private static void mapRequestWithWorkerInTheHandler(Class<?> handlerClass) {
        // get all methods from this class using reflect
        Method[] handlerMethods = ReflectionUtil.getMethods(handlerClass);

        String requestRootPath = "";
        // get Handler's root request path
        Handler handlerAnnotationObj = handlerClass.getAnnotation(Handler.class);
        String rootPathFromAnno = handlerAnnotationObj.path();
        if (StringUtils.isNotEmpty(rootPathFromAnno)) {
            requestRootPath += rootPathFromAnno + "/";
        }


        if (ArrayUtils.isNotEmpty(handlerMethods)) {
            for (Method handlerMethod : handlerMethods) {
                // check RequestMapper annotation on this method
                if (handlerMethod.isAnnotationPresent(HandlesRequest.class)) {
                    HandlesRequest annotationObj = handlerMethod.getAnnotation(HandlesRequest.class);

                    // fetch from annotation metadata
                    String requestMethod = annotationObj.method().name();
                    String requestPath = requestRootPath+ annotationObj.path();

                    // seal up into request map
                    Request request = new Request(requestMethod, requestPath);
                    Worker worker = new Worker(handlerClass, handlerMethod);
                    REQUEST_WORKER_MAP.put(request, worker);
                }
            }
        }
    }

    public static Worker getWorker(String requestMethodText, String requestPath) {
        Request request = new Request(requestMethodText, requestPath);
        return REQUEST_WORKER_MAP.get(request);
    }
}
