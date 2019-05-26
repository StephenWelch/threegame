package com.threegame.util

import com.threegame.engine.Mesh
import com.threegame.engine.Texture
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

        fun cube(length: Float, texture: Texture): Mesh {
            val vertices = floatArrayOf(
                // V0
                -length / 2, length / 2, length / 2,
                // V1
                -length / 2, -length / 2, length / 2,
                // V2
                length / 2, -length / 2, length / 2,
                // V3
                length / 2, length / 2, length / 2,
                // V4
                -length / 2, length / 2, -length / 2,
                // V5
                length / 2, length / 2, -length / 2,
                // V6
                -length / 2, -length / 2, -length / 2,
                // V7
                length / 2, -length / 2, -length / 2,

                // For text coords in top face
                // V8: V4 repeated
                -length / 2, length / 2, -length / 2,
                // V9: V5 repeated
                length / 2, length / 2, -length / 2,
                // V10: V0 repeated
                -length / 2, length / 2, length / 2,
                // V11: V3 repeated
                length / 2, length / 2, length / 2,

                // For text coords in right face
                // V12: V3 repeated
                length / 2, length / 2, length / 2,
                // V13: V2 repeated
                length / 2, -length / 2, length / 2,

                // For text coords in left face
                // V14: V0 repeated
                -length / 2, length / 2, length / 2,
                // V15: V1 repeated
                -length / 2, -length / 2, length / 2,

                // For text coords in bottom face
                // V16: V6 repeated
                -length / 2, -length / 2, -length / 2,
                // V17: V7 repeated
                length / 2, -length / 2, -length / 2,
                // V18: V1 repeated
                -length / 2, -length / 2, length / 2,
                // V19: V2 repeated
                length / 2, -length / 2, length / 2
            )

            val textureCoords = floatArrayOf(
                    0.0f, 0.0f,
                    0.0f, length / 2,
                    length / 2, length / 2,
                    length / 2, 0.0f,

                    0.0f, 0.0f,
                    length / 2, 0.0f,
                    0.0f, length / 2,
                    length / 2, length / 2,

                    // For text coords in top face
                    0.0f, length / 2,
                    length / 2, length / 2,
                    0.0f, 1.0f,
                    length / 2, 1.0f,

                    // For text coords in right face
                    0.0f, 0.0f,
                    0.0f, length / 2,

                    // For text coords in left face
                    length / 2, 0.0f,
                    length / 2, length / 2,

                    // For text coords in bottom face
                    length / 2, 0.0f,
                    1.0f, 0.0f,
                    length / 2, length / 2,
                    1.0f, length / 2
            )

            val indices = intArrayOf(
                    // Front face
                    0, 1, 3, 3, 1, 2,
                    // Top Face
                    8, 10, 11, 9, 8, 11,
                    // Right face
                    12, 13, 7, 5, 12, 7,
                    // Left face
                    14, 15, 6, 4, 14, 6,
                    // Bottom face
                    16, 18, 19, 17, 16, 19,
                    // Back face
                    4, 6, 7, 5, 4, 7
            )

            return Mesh(vertices, textureCoords, indices, texture)
        }

        fun sinf(value: Float) = Math.sin(value.toDouble()).toFloat()
        fun cosf(value: Float) = Math.cos(value.toDouble()).toFloat()
        fun tanf(value: Float) = Math.tan(value.toDouble()).toFloat()
        fun toRadiansf(value: Float) = Math.toRadians(value.toDouble()).toFloat()


    }

}