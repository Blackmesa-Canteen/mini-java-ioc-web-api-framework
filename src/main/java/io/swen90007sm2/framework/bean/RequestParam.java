package io.swen90007sm2.framework.bean;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * data bean for incoming request
 *
 * @author xiaotian
 */
public class RequestParam {

    /*
     * Map of request param k-v, e.g. "Age": 3
     */
    private Map<String, Object> requestParamMap;

    public boolean isEmptyParam() {
        return MapUtils.isEmpty(requestParamMap);
    }

    public RequestParam() {
    }

    public RequestParam(Map<String, Object> requestParamMap) {
        this.requestParamMap = requestParamMap;
    }

    public Map<String, Object> getRequestParamMap() {
        return requestParamMap;
    }

    public void setRequestParamMap(Map<String, Object> requestParamMap) {
        this.requestParamMap = requestParamMap;
    }

    @Override
    public String toString() {
        return "RequestParam{" +
                "requestParamMap=" + requestParamMap +
                '}';
    }
}
