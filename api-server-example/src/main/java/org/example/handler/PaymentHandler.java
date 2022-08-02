package org.example.handler;

import io.swen90007sm2.framework.annotation.ioc.AutoInjected;
import io.swen90007sm2.framework.annotation.mvc.Handler;
import io.swen90007sm2.framework.annotation.mvc.HandlesRequest;
import io.swen90007sm2.framework.annotation.mvc.QueryParam;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.common.constant.RequestMethod;
import org.example.blo.IPaymentBlo;
import org.example.constant.StatusCodeEnume;

/**
 * @author 996Worker
 * @description
 * @create 2022-08-02 21:29
 */
@Handler(path = "/payment")
public class PaymentHandler {


    @AutoInjected
    private IPaymentBlo paymentBlo;

    @HandlesRequest(path = "/add", method = RequestMethod.GET)
    public R checkSomething(@QueryParam(value = "num") int num) {
        int res = paymentBlo.addOne(num);
        return R.ok().setData(res);
    }
}