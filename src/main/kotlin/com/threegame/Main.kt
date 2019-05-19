package com.threegame

import com.threegame.engine.GameEngine
import com.threegame.engine.GlfwWindow
import com.threegame.game.DummyGame

fun main(args: Array<String>) {

    val gameThread = Thread {
        val window = GlfwWindow("Game", 600, 480, GlfwWindow.VsyncMode.NONE, false)
        val game = DummyGame()
        val engine = GameEngine(window, game)

        engine.run()
    }.start()



}