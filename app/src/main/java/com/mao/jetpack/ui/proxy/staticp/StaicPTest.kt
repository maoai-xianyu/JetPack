package com.mao.jetpack.ui.proxy.staticp

/**
 * @author zhangkun
 * @time 2022/4/13 19:43
 * @Description
 */
internal object StaicPTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val liLei = LiLei()
        val clienService = ClienService()
        clienService.setMassag(liLei)
        clienService.washSpa()
    }
}