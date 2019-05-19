package com.threegame.game

import com.threegame.engine.GlfwWindow
import com.threegame.engine.IGameLogic
import com.threegame.engine.Mesh
import org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN
import org.lwjgl.glfw.GLFW.GLFW_KEY_UP

class DummyGame : IGameLogic {

    private var direction = 0
    private var color = 0.0f
    private val renderer = Renderer(Mesh(floatArrayOf(
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    )))

    override fun init() {}
    override fun input(window: GlfwWindow) {
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            direction = 1
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            direction = -1
        } else {
            direction = 0
        }
    }

    override fun update(interval: Float) {
        color += direction * 0.01f
        if (color > 1) {
            color = 1.0f
        } else if (color < 0) {
            color = 0.0f
        }

    }

    override fun render(window: GlfwWindow) {
        renderer.render(window)
    }

    override fun cleanup() {
        renderer.cleanup()
    }
}