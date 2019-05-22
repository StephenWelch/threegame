package com.threegame.engine

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryUtil
import java.nio.FloatBuffer
import java.nio.IntBuffer

class Mesh(val vertices: FloatArray, val textureCoords: FloatArray, val indices: IntArray, val texture: Texture) {

    val vaoId: Int
    val vboIds: MutableList<Int>

    val vertexCount = indices.size

    init {
        // Allocate off-heap memory
        val vertexBuffer: FloatBuffer = MemoryUtil.memAllocFloat(vertices.size)
        val textureBuffer: FloatBuffer = MemoryUtil.memAllocFloat(textureCoords.size)
        val indexBuffer: IntBuffer = MemoryUtil.memAllocInt(indices.size)

        // Put vertices in the buffers, reset buffer index to 0
        vertexBuffer.put(vertices).flip()
        textureBuffer.put(textureCoords).flip()
        indexBuffer.put(indices).flip()

        vaoId = glGenVertexArrays()
        glBindVertexArray(vaoId)

        var vboId = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, vboId)
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)

        var textureVboId = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, textureVboId)
        glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW)
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0)

        var indexVboId = glGenBuffers()
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexVboId)
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW)

        vboIds = arrayListOf(vboId, textureVboId, indexVboId)

        // Unbind everything
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glBindVertexArray(0)

        MemoryUtil.memFree(vertexBuffer)
        MemoryUtil.memFree(textureBuffer)
        MemoryUtil.memFree(indexBuffer)
    }

    fun render() {
        glActiveTexture(GL_TEXTURE0)
        glBindTexture(GL11.GL_TEXTURE_2D, texture.id)

        glBindVertexArray(vaoId)
        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)

        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0)

        glDisableVertexAttribArray(0)
        glDisableVertexAttribArray(1)
        glBindVertexArray(0)
    }

    fun cleanup() {
        glDisableVertexAttribArray(0)

        glBindBuffer(GL_ARRAY_BUFFER, 0)
        vboIds.forEach(::glDeleteBuffers)

        glBindVertexArray(0)
        glDeleteVertexArrays(vaoId)
    }

}