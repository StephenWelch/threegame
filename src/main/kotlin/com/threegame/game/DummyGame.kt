package com.threegame.game

import com.threegame.engine.GameObject
import com.threegame.engine.GlfwWindow
import com.threegame.engine.IGameLogic
import com.threegame.util.Util
import org.joml.Vector3f
import org.lwjgl.glfw.GLFW.*

class DummyGame : IGameLogic {

    private var direction = Vector3f()
    private var color = 0.0f
    private val gameObject: GameObject
    private val renderer: Renderer

    init {
        val mesh = Util.cube(1f, 1f, 1f)
        gameObject = GameObject(
                mesh,
                Vector3f(0f, 0f, -2f)
        )
        renderer = Renderer(gameObject)
    }

    override fun init() {

    }

    override fun input(window: GlfwWindow) {
        direction = Vector3f(0f, 0f, 0f)
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            direction.add(1f, 0f, 0f)
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            direction.add(-1f, 0f, 0f)
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            direction.add(0f, 1f, 0f)
        }
        if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            direction.add(0f, -1f, 0f)
        }
    }

    override fun update(interval: Float) {
        val rotAmt = Math.toRadians(20.0).toFloat()
        val rotVec = Vector3f(rotAmt).mul(direction)
        gameObject.rotation = gameObject.rotation.add(rotVec)
    }

    override fun render(window: GlfwWindow) {
        renderer.render(window)
    }

    override fun cleanup() {
        renderer.cleanup()
    }
}