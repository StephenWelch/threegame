package com.threegame.engine

import com.threegame.util.SynchronousLoop

class GameEngine(val window: GlfwWindow, val gameLogic: IGameLogic): Runnable {

    val INPUT_UPDATE_RATE_S = 1.0 / 120.0
    val LOGIC_UPDATE_RATE_S = 1.0 / 120.0
    val RENDER_UPDATE_RATE_S = 1.0 / 60.0

    private val inputLoop = SynchronousLoop(INPUT_UPDATE_RATE_S, {
        gameLogic.input(window)
    })
    private val logicLoop = SynchronousLoop(LOGIC_UPDATE_RATE_S, {
        gameLogic.update(LOGIC_UPDATE_RATE_S.toFloat())
    })
    private val renderLoop = SynchronousLoop(RENDER_UPDATE_RATE_S, {
        gameLogic.render(window)
        window.update()
    })

    override fun run() {
        try {
            window.init()
            gameLogic.init()
            while(!window.shouldClose()) {
                inputLoop.updateLoop()
                logicLoop.updateLoop()
                renderLoop.updateLoop()
            }
        } catch(e: Exception) {
            e.printStackTrace()
        } finally {
            gameLogic.cleanup()
        }
    }

}