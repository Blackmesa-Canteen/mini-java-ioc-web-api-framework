package org.example.filter;

import com.alibaba.fastjson.JSON;
import io.swen90007sm2.framework.annotation.filter.RequestFilter;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.core.web.filter.IRequestFilter;
import org.apache.commons.lang3.StringUtils;
import org.example.constant.StatusCodeEnume;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 996Worker
 * @description simulated https://blog.csdn.net/HLH_2021/article/details/119491890
 * @create 2022-08-02 23:44
 */

@RequestFilter(name = "authFilter")
public class AuthFilter implements IRequestFilter {
    @Override
    public boolean doFilter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token= request.getHeader("auth");
        if (StringUtils.isBlank(token)) {
            responseWithFailure(response);
            return false;
        }
        return true;
    }

    private void responseWithFailure(HttpServletResponse response) throws IOException {
        R jsonObj = R.error(StatusCodeEnume.LOGIN_PASSWORD_EXCEPTION.getCode(), "auth failed");
        if (response != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JSON.toJSON(jsonObj).toString();
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }
}