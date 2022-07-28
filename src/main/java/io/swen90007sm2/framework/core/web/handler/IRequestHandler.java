package io.swen90007sm2.framework.core.web.handler;

import io.swen90007sm2.framework.bean.RequestSessionBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IRequestHandler {

    void handle(HttpServletRequest req, HttpServletResponse resp, RequestSessionBean requestSessionBean);
}
