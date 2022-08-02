package org.example.blo.impl;

import io.swen90007sm2.framework.annotation.mvc.Blo;
import org.example.blo.IPaymentBlo;

/**
 * @author 996Worker
 * @description
 * @create 2022-08-02 21:32
 */
@Blo
public class PaymentBlo implements IPaymentBlo {

    @Override
    public int addOne(int num) {

        return num + 1;
    }
}