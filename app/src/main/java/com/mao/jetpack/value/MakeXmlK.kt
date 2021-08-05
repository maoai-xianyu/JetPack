package com.mao.jetpack.value

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.PrintWriter

object MakeXmlK {
    private const val rootPath = "D:\\layoutroot\\values-{0}x{1}\\"
    private const val dw = 320f
    private const val dh = 480f
    private const val WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n"
    private const val HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n"
    @JvmStatic
    fun main(args: Array<String>) {
        makeString(320, 480)
        makeString(480, 800)
        makeString(480, 854)
        makeString(540, 960)
        makeString(600, 1024)
        makeString(720, 1184)
        makeString(720, 1196)
        makeString(720, 1280)
        makeString(768, 1024)
        makeString(800, 1280)
        makeString(1080, 1812)
        makeString(1080, 1920)
        makeString(1440, 2560)
    }

    fun makeString(w: Int, h: Int) {
        val sb = StringBuffer()
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n")
        sb.append("<resources>")
        val cellw = w / dw
        println("w= " + w + " , h= " + h + " , cellw= " + cellw + " , dw= " + dw)
        for (i in 1..319) {
            sb.append(
                WTemplate.replace("{0}", i.toString() + "")
                    .replace("{1}", change(cellw * i).toString() + "")
            )
            println("i = " + i + " , cellw*i = " + cellw * i)
        }
        sb.append(WTemplate.replace("{0}", "320").replace("{1}", w.toString() + ""))
        sb.append("</resources>")
        val sb2 = StringBuffer()
        sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n")
        sb2.append("<resources>")
        val cellh = h / dh
        for (i in 1..479) {
            sb2.append(
                HTemplate.replace("{0}", i.toString() + "")
                    .replace("{1}", change(cellh * i).toString() + "")
            )
        }
        sb2.append(HTemplate.replace("{0}", "480").replace("{1}", h.toString() + ""))
        sb2.append("</resources>")
        val path = rootPath.replace("{0}", h.toString() + "").replace("{1}", w.toString() + "")
        val rootFile = File(path)
        if (!rootFile.exists()) {
            rootFile.mkdirs()
        }
        val layxFile = File(path + "lay_x.xml")
        val layyFile = File(path + "lay_y.xml")
        try {
            var pw = PrintWriter(FileOutputStream(layxFile))
            pw.print(sb.toString())
            pw.close()
            pw = PrintWriter(FileOutputStream(layyFile))
            pw.print(sb2.toString())
            pw.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun change(a: Float): Float {
        val temp = (a * 100).toInt()
        return temp / 100f
    }
}