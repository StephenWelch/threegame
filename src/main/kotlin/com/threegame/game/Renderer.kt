package com.threegame.game

import com.threegame.engine.GlfwWindow
import com.threegame.engine.ShaderProgram
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryUtil
import java.nio.FloatBuffer

class Renderer {

    val shaderProgram: ShaderProgram
    val vaoId: Int
    val vboId: Int

    init {
        shaderProgram = ShaderProgram("/home/stephen/code/personal/threegame/src/main/resources/vertex.vs", "/home/stephen/code/personal/threegame/src/main/resources/vertex.vs")
        shaderProgram.link()

        val vertices: FloatArray = floatArrayOf(
                0.0f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f
        )

        // Allocate off-heap memory
        val vertexBuffer: FloatBuffer = MemoryUtil.memAllocFloat(vertices.size)
        // Put vertices in the buffer, reset buffer index to 0
        vertexBuffer.put(vertices).flip()

        vaoId = glGenVertexArrays()
        glBindVertexArray(vaoId)

        vboId = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, vboId)
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW)

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)

        // Unbind everything
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glBindVertexArray(0)

        MemoryUtil.memFree(vertexBuffer)
    }

    fun render(window: GlfwWindow) {
//        println(GLFW.glfwGetCurrentContext())
        window.clear()

        if(window.resized) {
            glViewport(0, 0, window.width, window.height)
            window.resized = false
        }

        shaderProgram.bind()

        glBindVertexArray(vaoId)
        glEnableVertexAttribArray(0)

        glDrawArrays(GL_TRIANGLES, 0, 3)

        glDisableVertexAttribArray(0)
        glBindVertexArray(0)

        shaderProgram.unbind()

    }

    fun cleanup() {
        shaderProgram.cleanup()
        glDisableVertexAttribArray(0)
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glDeleteBuffers(vboId)
        glBindVertexArray(0)
        glDeleteVertexArrays(vaoId)
    }

}