package io.swen90007sm2.framework.bean;

import org.apache.commons.collections4.MapUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * data bean for one request-response serving session
 *
 * @author xiaotian
 */
public class RequestSessionBean {

    /**
     * the handler method bean that is needed for this serving session
     */
    private Worker workerNeeded;

    // TODO
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;

    /**
     * param: /get/id/2
     */
    private Map<String, String> pathVariableParameterMap;

    /**
     * param /get?id=2
     */
    private Map<String, String> queryParameterMap;

    /**
     * param: json body
     */
    private String jsonBodyString;

//    public boolean isParamEmpty() {
//        return MapUtils.isEmpty(pathVariableParameterMap) && MapUtils.isEmpty(queryParameterMap);
//    }

    public Map<String, String> getPathVariableParameterMap() {
        return pathVariableParameterMap;
    }

    public void setPathVariableParameterMap(Map<String, String> pathVariableParameterMap) {
        this.pathVariableParameterMap = pathVariableParameterMap;
    }

    public Map<String, String> getQueryParameterMap() {
        return queryParameterMap;
    }

    public void setQueryParameterMap(Map<String, String> queryParameterMap) {
        this.queryParameterMap = queryParameterMap;
    }

    public String getJsonBodyString() {
        return jsonBodyString;
    }

    public void setJsonBodyString(String jsonBodyString) {
        this.jsonBodyString = jsonBodyString;
    }

    public Worker getWorkerNeeded() {
        return workerNeeded;
    }

    public void setWorkerNeeded(Worker workerNeeded) {
        this.workerNeeded = workerNeeded;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public String toString() {
        return "RequestSessionBean{" +
                "workerNeeded=" + workerNeeded +
                ", pathVariableParameterMap=" + pathVariableParameterMap +
                ", queryParameterMap=" + queryParameterMap +
                ", jsonBodyString='" + jsonBodyString + '\'' +
                '}';
    }
}
