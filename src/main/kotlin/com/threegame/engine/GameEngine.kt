package com.threegame.engine

import com.threegame.util.SynchronousLoop

class GameEngine(val window: GlfwWindow, val mouseInput: MouseInput, val gameLogic: IGameLogic): Runnable {

    val kInputUpdateRateS = 1.0 / 120.0
    val kLogicUpdateRateS = 1.0 / 120.0
    val kRenderUpdateRateS = 1.0 / 60.0

    private val inputLoop = SynchronousLoop(kInputUpdateRateS, {
        mouseInput.input(window)
        gameLogic.input(window, mouseInput)
    })
    private val logicLoop = SynchronousLoop(kLogicUpdateRateS, {
        gameLogic.update(kLogicUpdateRateS.toFloat(), mouseInput)
    })
    private val renderLoop = SynchronousLoop(kRenderUpdateRateS, {
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