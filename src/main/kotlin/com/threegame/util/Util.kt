package com.threegame.util

import org.lwjgl.opengl.GL11.GL_FALSE
import org.lwjgl.opengl.GL11.GL_TRUE
import java.io.InputStream


fun Boolean.glValue(): Int = if(this) GL_TRUE else GL_FALSE

class Util {

    companion object {
        fun getSysTime(): Double = System.currentTimeMillis() / 1000.0

        @Throws(Exception::class)
        fun loadResourceAsString(fileName: String): String = loadResource(fileName).readBytes().toString(Charsets.UTF_8)
        fun loadResource(fileName: String): InputStream {
            return Util::class.java.classLoader.getResourceAsStream(fileName)
        }

        fun cube(length: Float, width: Float, height: Float): FloatArray = floatArrayOf(
                // VO
                -width / 2, height / 2, length / 2,
                // V1
                -width / 2, -height / 2, length / 2,
                // V2
                width / 2, -height / 2, length / 2,
                // V3
                width / 2, height / 2, length / 2,
                // V4
                -width / 2, height / 2, -length / 2,
                // V5
                width / 2, height / 2, -length / 2,
                // V6
                -width / 2, -height / 2, -length / 2,
                // V7
                width / 2, -height / 2, -length / 2
        )
    }

}