package com.threegame.util

import org.lwjgl.opengl.GL11
import java.io.File


fun Boolean.glValue(): Int = if(this) GL11.GL_TRUE else GL11.GL_FALSE

class Util {

    companion object {
        fun getSysTime(): Double = System.currentTimeMillis() / 1000.0

        @Throws(Exception::class)
        fun loadResource(fileName: String): String = File(fileName).inputStream().readBytes().toString(Charsets.UTF_8)
    }

}