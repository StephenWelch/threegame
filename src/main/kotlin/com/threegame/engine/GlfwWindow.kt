package com.threegame.engine

import com.threegame.util.glValue
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWVidMode
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL

class GlfwWindow(val title: String, var width: Int, var height: Int, val vsyncMode: VsyncMode = VsyncMode.NONE, val isFullscreen: Boolean = false, val resizable: Boolean = true) {

    private var vidMode: GLFWVidMode? = null

    val windowHandle: Long
    var resized = false

    enum class VsyncMode(val mode: Int) {
        NONE(0),
        SINGLE_BUFFER(1),
        DOUBLE_BUFFER(2)
    }

    init {
        GLFWErrorCallback.createPrint(System.err).set()

        if(!glfwInit()) throw IllegalStateException("Unable to initialize GLFW")

        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, resizable.glValue())
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE)

        windowHandle = glfwCreateWindow(width, height, title, NULL, NULL)
        if(windowHandle == NULL) throw RuntimeException("Failed to create the GLFW window")

        if(resizable) {
            glfwSetFramebufferSizeCallback(windowHandle) { _, width, height ->
                this.width = width
                this.height = height
                this.resized = true
            }
        }

        glfwSetKeyCallback(windowHandle) { _, key, _, action, _ -> handleKeyCallback(key, action)}

        vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor())

        centerWindow()

        glfwMakeContextCurrent(windowHandle)

        if(vsyncMode != VsyncMode.NONE) glfwSwapInterval(vsyncMode.mode)

        glfwShowWindow(windowHandle)

        GL.createCapabilities()

        glEnable(GL_DEPTH_TEST)
        clearColor(0.0f, 0.0f, 0.0f, 0.0f)
    }

    fun init() {

    }

    fun update() {
        glfwSwapBuffers(windowHandle)
        glfwPollEvents()
    }

    fun clear() {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
    }

    fun clearColor(red: Float, green: Float, blue: Float, alpha: Float) {
        glClearColor(red, green, blue, alpha)
    }

    fun centerWindow() {
        glfwSetWindowPos(windowHandle, (vidMode!!.width() - width) / 2, (vidMode!!.height() - height) / 2)
    }

    fun isKeyPressed(keyCode: Int): Boolean {
        return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS
    }

    fun shouldClose(): Boolean = glfwWindowShouldClose(windowHandle)

    private fun handleKeyCallback(key: Int, action: Int) {
        print("Key " + key.toChar() + " Event")
        if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
            glfwSetWindowShouldClose(windowHandle, true)
            print("ESC Pressed. Closing window.")
        }
    }

}