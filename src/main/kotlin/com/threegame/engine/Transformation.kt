package com.threegame.engine

import org.joml.Matrix4f
import org.joml.Vector3f

class Transformation {

    private val projectionMatrix: Matrix4f = Matrix4f()
    private val worldMatrix: Matrix4f = Matrix4f()

    fun getProjectionMatrix(fieldOfView: Float, width: Float, height: Float, zNear: Float, zFar: Float): Matrix4f {
        val aspectRatio = width / height
        projectionMatrix.identity()
        projectionMatrix.perspective(fieldOfView, aspectRatio, zNear, zFar)
        return projectionMatrix
    }

    fun getWorldMatrix(offset: Vector3f, rotation: Vector3f, scale: Float): Matrix4f {
        worldMatrix
                .identity()
                .translate(offset)
                .rotateX(Math.toRadians(rotation.x.toDouble()).toFloat())
                .rotateY(Math.toRadians(rotation.y.toDouble()).toFloat())
                .rotateZ(Math.toRadians(rotation.z.toDouble()).toFloat())
                .scale(scale)
        return worldMatrix
    }

}