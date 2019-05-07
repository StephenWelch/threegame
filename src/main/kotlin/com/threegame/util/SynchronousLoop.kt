package com.threegame.util

import com.threegame.Util

class SynchronousLoop(private val updateRateSeconds: Double, val loopFunction: () -> Unit, val getTime: () -> Double = { Util.getSysTime()}) {

    private var lastUpdate = getTime()

    fun updateLoop() {

        if(getTime() - lastUpdate >= updateRateSeconds) {
            loopFunction()
            lastUpdate = getTime()
        }

    }


}