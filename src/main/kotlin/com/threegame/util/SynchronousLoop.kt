package com.threegame.util

import com.threegame.Util
import jdk.nashorn.internal.objects.NativeDate.getTime

class SynchronousLoop(private val updateRateSeconds: Double, val loopFunction: () -> Unit, val getTime: () -> Double = {Util.getSysTime()}) {

    private val lastUpdate = getTime()

    fun updateLoop() {

        if(getTime() - lastUpdate >= updateRateSeconds * 1000.0) {
            loopFunction()
        }

    }


}