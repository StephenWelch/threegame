package com.threegame.engine

import com.threegame.util.Util
import org.joml.Vector3f

class FpsCamera: Camera() {

    override fun translateBy(x: Float, y: Float, z: Float) = translateBy(Vector3f(x, y, z))

    override fun translateBy(offset: Vector3f) {
        if(offset.z != 0f) {
            position.x += Util.sinf(rotation.y) * -1f * offset.z
            position.z += Util.sinf(rotation.y) * offset.z
        }
        if(offset.x != 0f) {
            position.x += Util.sinf(rotation.y - 90) * -1f * offset.x
            position.z += Util.cosf(rotation.y - 90) * offset.x
        }
        position.y = offset.y
    }

    override fun rotateBy(x: Float, y: Float, z: Float) = rotateBy(Vector3f(x, y, z))

    override fun rotateBy(offset: Vector3f) {
        rotation.x += offset.x
        rotation.y += offset.y
        rotation.z += offset.z
    }

    override fun setRotation(x: Float, y: Float, z: Float) = setRotation(Vector3f(x, y, z))

    override fun setRotation(rotation: Vector3f) {
        this.rotation.x = rotation.x
        this.rotation.y = rotation.y
        this.rotation.z = rotation.z
    }

    override fun setPosition(x: Float, y: Float, z: Float) = setRotation(Vector3f(x, y, z))

    override fun setPosition(position: Vector3f) {
        this.position.x = position.x
        this.position.y = position.y
        this.position.x = position.z
    }

}