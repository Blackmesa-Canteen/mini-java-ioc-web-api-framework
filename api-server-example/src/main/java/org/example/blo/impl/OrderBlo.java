package org.example.blo.impl;

import io.swen90007sm2.framework.annotation.ioc.AutoInjected;
import io.swen90007sm2.framework.annotation.mvc.Blo;
import org.example.blo.IOrderBlo;
import org.example.blo.IUserBlo;

@Blo
public class OrderBlo implements IOrderBlo {

    @AutoInjected
    IUserBlo userBlo;

    @Override
    public boolean addOrder(long test) {
        return userBlo.addUser(null);
    }
}
