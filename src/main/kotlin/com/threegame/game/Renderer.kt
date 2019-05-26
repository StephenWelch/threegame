package com.threegame.game

import com.threegame.engine.*
import org.lwjgl.opengl.GL30.glViewport

class Renderer(val camera: Camera, vararg val gameObjects: GameObject) {

    val kFieldOfView: Float = Math.toRadians(60.0).toFloat()
    val kZNear: Float = 0.01f
    val kZFar: Float = 1000.0f

    val transformation = Transformation()

    private val shaderProgram: ShaderProgram

    init {
        shaderProgram = ShaderProgram("vertex.vs", "fragment.fs")
        shaderProgram.link()
        shaderProgram.createUniform("projectionMatrix")
        shaderProgram.createUniform("modelViewMatrix")
        shaderProgram.createUniform("texture_sampler", 0)
    }

    fun render(window: GlfwWindow) {
        window.clear()

        if(window.resized) {
            glViewport(0, 0, window.width, window.height)
            window.resized = false
        }

        shaderProgram.bind()
        shaderProgram.setUniform("projectionMatrix", transformation.getProjectionMatrix(kFieldOfView, window.width.toFloat(), window.height.toFloat(), kZNear, kZFar))

        for(gameObject in gameObjects) {
            shaderProgram.setUniform("modelViewMatrix", transformation.getModelViewMatrix(gameObject, transformation.getViewMatrix(camera)))
            gameObject.mesh.render()
        }

        shaderProgram.unbind()

    }

    fun cleanup() {
        shaderProgram.cleanup()
        gameObjects.forEach { it.mesh.cleanup() }
    }

}