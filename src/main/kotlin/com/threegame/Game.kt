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

    fun logicUpdate() {
        println("Update logic")
    }

    fun renderUpdate() {
        println("Update render")
    }

    private fun getTime(): Long = System.currentTimeMillis()

}