package com.threegame.engine

import org.lwjgl.opengl.GL15.glBindBuffer
import org.lwjgl.opengl.GL15.glDeleteBuffers
import org.lwjgl.opengl.GL20.glDisableVertexAttribArray
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryUtil
import java.nio.FloatBuffer
import java.nio.IntBuffer

class Mesh(val vertices: FloatArray, val indices: IntArray) {

    val vaoId: Int
    val vboId: Int
    val indexVboId: Int

    val vertexCount = indices.size

    init {
        // Allocate off-heap memory
        val vertexBuffer: FloatBuffer = MemoryUtil.memAllocFloat(vertices.size)
        val indexBuffer: IntBuffer = MemoryUtil.memAllocInt(indices.size)

        // Put vertices in the buffers, reset buffer index to 0
        vertexBuffer.put(vertices).flip()
        indexBuffer.put(indices).flip()

        vaoId = glGenVertexArrays()
        glBindVertexArray(vaoId)

        vboId = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, vboId)
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)

        indexVboId = glGenBuffers()
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexVboId)
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW)


        // Unbind everything
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glBindVertexArray(0)

        MemoryUtil.memFree(vertexBuffer)
        MemoryUtil.memFree(indexBuffer)
    }

    fun cleanup() {
        glDisableVertexAttribArray(0)

        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glDeleteBuffers(vboId)
        glDeleteBuffers(indexVboId)

        glBindVertexArray(0)
        glDeleteVertexArrays(vaoId)
    }

}