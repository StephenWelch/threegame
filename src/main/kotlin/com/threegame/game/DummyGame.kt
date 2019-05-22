package com.threegame.game

import com.threegame.engine.GameObject
import com.threegame.engine.GlfwWindow
import com.threegame.engine.IGameLogic
import com.threegame.engine.Texture
import com.threegame.util.Util
import org.joml.Vector3f
import org.lwjgl.glfw.GLFW.*

class DummyGame : IGameLogic {

    private var rotationDirection = Vector3f()
    private var transformDirection = Vector3f()
    private var color = 0.0f
    private val gameObject: GameObject
    private val renderer: Renderer

    init {
        val mesh = Util.cube(1f, Texture("grassblock.png"))
        gameObject = GameObject(
                mesh,
                Vector3f(0f, 0f, -2f)
        )
        renderer = Renderer(gameObject)
    }

    override fun init() {

    }

    override fun input(window: GlfwWindow) {
        rotationDirection = Vector3f(0f, 0f, 0f)
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            rotationDirection.add(1f, 0f, 0f)
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            rotationDirection.add(-1f, 0f, 0f)
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            rotationDirection.add(0f, 1f, 0f)
        }
        if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            rotationDirection.add(0f, -1f, 0f)
        }

        transformDirection = Vector3f(0f, 0f, 0f)
        if (window.isKeyPressed(GLFW_KEY_W)) {
            transformDirection.add(0f, 0f, -1f)
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            transformDirection.add(-1f, 0f, 0f)
        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            transformDirection.add(0f, 0f, 1f)
        }
        if (window.isKeyPressed(GLFW_KEY_D)) {
            transformDirection.add(1f, 0f, 0f)
        }
        if(window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            transformDirection.add(0f, -1f, 0f)
        }
        if(window.isKeyPressed(GLFW_KEY_SPACE)) {
            transformDirection.add(0f, 1f, 0f)
        }
    }

    override fun update(interval: Float) {
        val rotAmt = Math.toRadians(30.0).toFloat()
        val rotVec = Vector3f(rotAmt).mul(rotationDirection)
        val transAmt = 0.1f
        val transVec = Vector3f(transAmt).mul(transformDirection)
        gameObject.rotation = gameObject.rotation.add(rotVec)
        gameObject.position = gameObject.position.add(transVec)
    }

    override fun render(window: GlfwWindow) {
        renderer.render(window)
    }

    override fun cleanup() {
        renderer.cleanup()
    }
}