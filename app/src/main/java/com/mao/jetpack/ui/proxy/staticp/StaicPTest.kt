package com.mao.jetpack.ui.proxy.staticp

import android.content.res.Resources
import android.util.TypedValue
import com.google.gson.Gson
import com.mao.jetpack.ui.proxy.HomeShowListInfo

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

        (Int::toFloat)(1)
        Int::toFloat.invoke(1)

        (String::method)("hhhhh", 1)


        println(" -- " + (String::method)("hhhhh", 1))

        val a: String.(Int) -> String = String::method

        println(a.invoke("hhhhh", 1))

        println("hhhhhh".a(2))

        val b: (String, Int) -> String = String::method


        val c: String.(Int) -> String = ::method2

        val d = 200f

        //println("${d.dp}")



        val str :String  = """
             {
             "title": "精彩现场",
             "schemaUrl": "https://h5.dianping.com/app/myshow/index.html?utm_source=my_firstpage_excellentshow#/",
             "recordList": [ ]
             }
        """.trimIndent()

        val gson = Gson()

        val fromJson = gson.fromJson<HomeShowListInfo>(str, HomeShowListInfo::class.java)

        println(fromJson)

        val recordList = fromJson.recordList
        println("recordList $recordList   size ${recordList.size}")


    }



}


fun String.method(i: Int): String {
    return i.toString()
}

fun method2(a: String, b: Int): String {
    return "$a + $b"
}

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )