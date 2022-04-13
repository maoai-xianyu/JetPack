package com.mao.jetpack.ui.proxy.staticp;

import com.mao.jetpack.ui.proxy.Massag;

/**
 * @author zhangkun
 * @time 2022/4/13 19:39
 * @Description
 */
class ClienService implements Massag {

    private Massag massag;

    public void setMassag(Massag massag) {
        this.massag = massag;
    }

    private void before() {
        System.out.println("引导客户 进入房间");
    }

    private void after() {
        System.out.println("引导客户 付钱");
    }

    @Override
    public void washSpa() {
        before();
        massag.washSpa();
        after();
    }
}
