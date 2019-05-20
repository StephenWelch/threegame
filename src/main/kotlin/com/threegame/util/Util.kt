package com.threegame.util

import org.lwjgl.opengl.GL11
import java.io.File


fun Boolean.glValue(): Int = if(this) GL11.GL_TRUE else GL11.GL_FALSE

class Util {

    companion object {
        fun getSysTime(): Double = System.currentTimeMillis() / 1000.0

        @Throws(Exception::class)
        fun loadResource(fileName: String): String = File(fileName).inputStream().readBytes().toString(Charsets.UTF_8)

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