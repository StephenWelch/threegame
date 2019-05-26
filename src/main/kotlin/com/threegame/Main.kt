package com.threegame

import com.threegame.engine.GameEngine
import com.threegame.engine.GlfwWindow
import com.threegame.engine.MouseInput
import com.threegame.game.DummyGame

fun main() {

    Thread {
        val window = GlfwWindow("Game", 600, 480, GlfwWindow.VsyncMode.NONE, false)
        val mouseInput = MouseInput(window)
        val game = DummyGame()
        val engine = GameEngine(window, mouseInput, game)

        engine.run()
    }.start()



}