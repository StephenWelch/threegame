package com.threegame.util

class SynchronousLoop(private val updateRateSeconds: Double, val loopFunction: () -> Unit, val getTime: () -> Double = { Util.getSysTime()}) {

    private var lastUpdate = getTime()
    private var firstRun = true

    fun updateLoop() {

        if(getTime() - lastUpdate >= updateRateSeconds || firstRun) {
            loopFunction()
            firstRun = false
            lastUpdate = getTime()
        }

    }


}