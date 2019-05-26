package com.threegame.engine

import org.joml.Vector2f
import org.lwjgl.glfw.GLFW.*

class MouseInput(val window: GlfwWindow) {

    val mouseDelta: Vector2f = Vector2f()
    val mousePos: Vector2f = Vector2f()
    val lastPos: Vector2f = Vector2f(-1f, -1f)

    var leftButton = false
    var rightButton = false

    var inWindow = false

    init {
        glfwSetCursorPosCallback(window.windowHandle) { _, xpos, ypos ->
            mousePos.x = xpos.toFloat()
            mousePos.y = ypos.toFloat()
        }
        glfwSetCursorEnterCallback(window.windowHandle) { _, entered ->
            inWindow = entered
        }
        glfwSetMouseButtonCallback(window.windowHandle) { _, button, action, _ ->
            leftButton = (button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS)
            rightButton = (button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS)
        }
    }

    fun input(window: GlfwWindow) {
        mouseDelta.set(0f, 0f)

        if(lastPos.x > 0 && lastPos.y > 0 && inWindow) {
            val delta = Vector2f(mousePos).sub(lastPos)
            if(delta.x != 0f) {
                mouseDelta.x = delta.x
            }
            if(delta.y != 0f) {
                mouseDelta.y = delta.y
            }
        }
        lastPos.set(mousePos)
    }

}