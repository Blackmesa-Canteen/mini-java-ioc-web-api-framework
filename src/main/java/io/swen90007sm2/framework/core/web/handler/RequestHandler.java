package io.swen90007sm2.framework.core.web.handler;

import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.bean.RequestSessionBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestHandler {

    void handle(HttpServletRequest req, HttpServletResponse resp, RequestSessionBean requestSessionBean);
}
