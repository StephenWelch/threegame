package com.threegame.engine

import org.lwjgl.opengl.GL15.glBindBuffer
import org.lwjgl.opengl.GL15.glDeleteBuffers
import org.lwjgl.opengl.GL20.glDisableVertexAttribArray
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryUtil
import java.nio.FloatBuffer

class Mesh(val vertices: FloatArray) {

    val vaoId: Int
    val vboId: Int

    init {
        // Allocate off-heap memory
        val vertexBuffer: FloatBuffer = MemoryUtil.memAllocFloat(vertices.size)
        // Put vertices in the buffer, reset buffer index to 0
        vertexBuffer.put(vertices).flip()

        vaoId = glGenVertexArrays()
        glBindVertexArray(vaoId)

        vboId = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, vboId)
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL30.GL_STATIC_DRAW)

        glVertexAttribPointer(0, 3, GL30.GL_FLOAT, false, 0, 0)

        // Unbind everything
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glBindVertexArray(0)

        MemoryUtil.memFree(vertexBuffer)
    }

    fun cleanup() {
        glDisableVertexAttribArray(0)
        glBindBuffer(GL30.GL_ARRAY_BUFFER, 0)
        glDeleteBuffers(vboId)
        glBindVertexArray(0)
        glDeleteVertexArrays(vaoId)
    }

}