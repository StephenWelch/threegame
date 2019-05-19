package com.threegame.game

import com.threegame.engine.GlfwWindow
import com.threegame.engine.Mesh
import com.threegame.engine.ShaderProgram
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30.*

class Renderer(val mesh: Mesh) {

    val shaderProgram: ShaderProgram

    init {
        shaderProgram = ShaderProgram("/home/stephen/code/personal/threegame/src/main/resources/vertex.vs", "/home/stephen/code/personal/threegame/src/main/resources/vertex.vs")
        shaderProgram.link()
    }

    fun render(window: GlfwWindow) {
//        println(GLFW.glfwGetCurrentContext())
        window.clear()

        if(window.resized) {
            glViewport(0, 0, window.width, window.height)
            window.resized = false
        }

        shaderProgram.bind()

        glBindVertexArray(mesh.vaoId)
        glEnableVertexAttribArray(0)

        GL11.glDrawElements(GL_TRIANGLES, mesh.vertexCount, GL_UNSIGNED_INT, 0)

        glDisableVertexAttribArray(0)
        glBindVertexArray(0)

        shaderProgram.unbind()

    }

    fun cleanup() {
        shaderProgram.cleanup()
        mesh.cleanup()
    }

}