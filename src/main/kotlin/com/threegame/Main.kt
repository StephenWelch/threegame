package com.threegame

fun main(args: Array<String>) {

    val game = Game()
    Thread(game).start()

}