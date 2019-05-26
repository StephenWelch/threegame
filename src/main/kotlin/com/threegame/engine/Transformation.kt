package com.threegame.engine

import com.threegame.util.Util
import org.joml.Matrix4f
import org.joml.Vector3f

class Transformation {

    private val projectionMatrix: Matrix4f = Matrix4f()
    private val modelViewMatrix: Matrix4f = Matrix4f()
    private val viewMatrix: Matrix4f = Matrix4f()

    fun getProjectionMatrix(fieldOfView: Float, width: Float, height: Float, zNear: Float, zFar: Float): Matrix4f {
        val aspectRatio = width / height
        projectionMatrix.identity()
        projectionMatrix.perspective(fieldOfView, aspectRatio, zNear, zFar)
        return projectionMatrix
    }

    fun getModelViewMatrix(gameObject: GameObject, viewMatrix: Matrix4f): Matrix4f {
        with(gameObject.rotation) {
            modelViewMatrix.identity().translate(gameObject.position).
                    rotateX(Util.toRadiansf(-this.x)).
                    rotateY(Util.toRadiansf(-this.y)).
                    rotateZ(Util.toRadiansf(-this.x))
        }

        return Matrix4f(viewMatrix).mul(modelViewMatrix)
    }

    fun getViewMatrix(camera: Camera): Matrix4f {
        viewMatrix.identity()
        with(camera.rotation) {
            // Rotate the camera on x axis then y axis
            viewMatrix.rotate(Util.toRadiansf(this.x), Vector3f(1f, 0f, 0f)).rotate(Util.toRadiansf(this.y), Vector3f(0f, 1f, 0f))
        }
        with(camera.position) {
            // Move the camera
            viewMatrix.translate(-this.x, -this.y, -this.z)
        }
        return viewMatrix
    }

}