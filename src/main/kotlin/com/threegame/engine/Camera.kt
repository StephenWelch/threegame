package com.threegame.engine

import org.joml.Vector3f

abstract class Camera(val position: Vector3f = Vector3f(0f, 0f, 0f), val rotation: Vector3f = Vector3f(0f, 0f, 0f)) {

    abstract fun translateBy(x: Float, y: Float, z: Float)
    abstract fun translateBy(offset: Vector3f)

    abstract fun rotateBy(x: Float, y: Float, z: Float)
    abstract fun rotateBy(offset: Vector3f)

    abstract fun setRotation(x: Float, y: Float, z: Float)
    abstract fun setRotation(rotation: Vector3f)

    abstract fun setPosition(x: Float, y: Float, z: Float)
    abstract fun setPosition(position: Vector3f)

}