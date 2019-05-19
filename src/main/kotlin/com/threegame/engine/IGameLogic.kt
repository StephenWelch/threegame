package com.threegame.engine

interface IGameLogic {

    fun init()
    fun input(window: GlfwWindow)
    fun update(interval: Float)
    fun render(window: GlfwWindow)
    fun cleanup()

}