package com.threegame.game

import com.threegame.engine.*
import com.threegame.util.Util
import org.joml.Vector3f
import org.lwjgl.glfw.GLFW.*

class DummyGame : IGameLogic {

    private val ROTATION_SENSITIVTY = Vector3f(0.5f, 0.5f, 0.5f)
    private val MOVE_AMOUNT = 0.1f

    private var rotAmt = Vector3f()
    private var transformDirection = Vector3f()
    private val gameObject: GameObject
    private val camera: Camera = FpsCamera()
    private val renderer: Renderer

    init {
        val mesh = Util.cube(1f, Texture("grassblock.png"))
        gameObject = GameObject(
                mesh,
                Vector3f(0f, 0f, -2f)
        )
        renderer = Renderer(camera, gameObject)
    }

    override fun init() {

    }

    override fun input(window: GlfwWindow, mouseInput: MouseInput) {

        rotAmt.set(0f, 0f, 0f)
        if(mouseInput.leftButton) {
            rotAmt.set(Vector3f(mouseInput.mouseDelta.y, mouseInput.mouseDelta.x, 0f))
        }

        transformDirection = Vector3f(0f, 0f, 0f)
        if (window.isKeyPressed(GLFW_KEY_W)) {
            transformDirection.z = -1f
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            transformDirection.x = -1f
        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            transformDirection.z = 1f
        }
        if (window.isKeyPressed(GLFW_KEY_D)) {
            transformDirection.x = 1f
        }
        if(window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            transformDirection.y = -1f
        }
        if(window.isKeyPressed(GLFW_KEY_SPACE)) {
            transformDirection.y = 1f
        }
    }

    override fun update(interval: Float, mouseInput: MouseInput) {
        val rotVec = Vector3f(rotAmt).mul(ROTATION_SENSITIVTY)
        val transVec = Vector3f(MOVE_AMOUNT).mul(transformDirection)
        camera.rotation.add(rotVec)
        camera.position.add(transVec)
    }

    override fun render(window: GlfwWindow) {
        renderer.render(window)
    }

    override fun cleanup() {
        renderer.cleanup()
    }
}