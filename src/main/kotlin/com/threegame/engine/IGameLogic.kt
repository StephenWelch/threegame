package com.threegame.engine

interface IGameLogic {

    fun init()
    fun input(window: GlfwWindow, mouseInput: MouseInput)
    fun update(interval: Float, mouseInput: MouseInput)
    fun render(window: GlfwWindow)
    fun cleanup()

}