package com.threegame.util

import com.threegame.engine.Mesh
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

        fun cube(length: Float, width: Float, height: Float): Mesh {
            val vertices = floatArrayOf(
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

            val colors = floatArrayOf(
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f
            )

            val indices = intArrayOf(
                    // Front face
                    0, 1, 3, 3, 1, 2,
                    // Top Face
                    4, 0, 3, 5, 4, 3,
                    // Right face
                    3, 2, 7, 5, 3, 7,
                    // Left face
                    6, 1, 0, 6, 0, 4,
                    // Bottom face
                    2, 1, 6, 2, 6, 7,
                    // Back face
                    7, 6, 4, 7, 4, 5
            )

            return Mesh(vertices, colors, indices)
        }
    }

}