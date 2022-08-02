package org.example.handler;

import io.swen90007sm2.framework.annotation.filter.AppliesRequestFilter;
import io.swen90007sm2.framework.annotation.mvc.Handler;
import io.swen90007sm2.framework.annotation.mvc.HandlesRequest;
import io.swen90007sm2.framework.annotation.mvc.PathVariable;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.common.constant.RequestMethod;

/**
 * @author 996Worker
 * @description
 * @create 2022-08-02 23:44
 */

@Handler(path = "/auth")
public class AuthTestHandler {

    @HandlesRequest(path = "/get/{id}", method = RequestMethod.GET)
    @AppliesRequestFilter(filterNames = {"authFilter"})
    public R someHandlerNeedAuth(@PathVariable("id") Long id) {
        return R.ok().setData("Oh yeah you passed");
    }
}