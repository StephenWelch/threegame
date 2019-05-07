package com.threegame

import com.threegame.util.SynchronousLoop

class Game: Runnable {

    val LOGIC_UPDATE_RATE_S = 1.0 / 120.0
    val RENDER_UPDATE_RATE_S = 1.0 / 60.0

    val logicLoop = SynchronousLoop(LOGIC_UPDATE_RATE_S, this::logicUpdate)
    val renderLoop = SynchronousLoop(RENDER_UPDATE_RATE_S, this::renderUpdate)

    override fun run() {
        while(true) {
            logicLoop.updateLoop()
            renderLoop.updateLoop()
        }
    }

    var startLogic = 0.0
    fun logicUpdate() {
        println("Logic dt: " + (1.0 / (Util.getSysTime() - startLogic)))
        startLogic = Util.getSysTime()
    }

    var startRender = 0.0
    fun renderUpdate() {
        println("Render dt: " + (1.0 / (Util.getSysTime() - startRender)))
        startRender = Util.getSysTime()
    }


}