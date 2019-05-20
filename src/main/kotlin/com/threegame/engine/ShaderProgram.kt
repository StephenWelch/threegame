package com.threegame.engine

import com.threegame.util.Util
import org.joml.Matrix4f
import org.lwjgl.opengl.GL20.*
import org.lwjgl.system.MemoryStack

class ShaderProgram(val vertexShaderPath: String, val fragmentShaderPath: String) {

    private val programId: Int

    private val vertexShaderId: Int
    private val fragmentShaderId:Int
    private val uniforms: MutableMap<String, Int> = HashMap()

    init {
        programId = glCreateProgram()

        if(programId == 0) throw RuntimeException("Could not create shader program.")

        vertexShaderId = createVertexShader(Util.loadResource("/home/stephen/code/personal/threegame/src/main/resources/vertex.vs"))
        fragmentShaderId = createFragmentShader(Util.loadResource("/home/stephen/code/personal/threegame/src/main/resources/fragment.fs"))
    }

    private fun createVertexShader(shaderCode: String): Int = createShader(shaderCode, GL_VERTEX_SHADER)


    private fun createFragmentShader(shaderCode: String): Int = createShader(shaderCode, GL_FRAGMENT_SHADER)


    private fun createShader(shaderCode: String, shaderType: Int): Int {
        val shaderId = glCreateShader(shaderType)
        if(shaderId == 0) throw RuntimeException("Error creating shader of type ${shaderType}.")

        glShaderSource(shaderId, shaderCode)
        glCompileShader(shaderId)

        if(glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) throw RuntimeException("Error compiling shader code: ${glGetShaderInfoLog(shaderId, 1024)}")

        glAttachShader(programId, shaderId)

        return shaderId
    }

    fun link() {
        glLinkProgram(programId)
        if(glGetProgrami(programId, GL_LINK_STATUS) == 0) throw RuntimeException("Error linking shader code: ${glGetProgramInfoLog(programId, 1024)}")

        if(vertexShaderId != 0) glDetachShader(programId, vertexShaderId)
        if(fragmentShaderId != 0) glDetachShader(programId, fragmentShaderId)

        glValidateProgram(programId)
        if(glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024))
    }

    fun createUniform(uniformName: String) {
        val uniformLocation = glGetUniformLocation(programId, uniformName)
        if(uniformLocation < 0) throw RuntimeException("Could not find uniform with name ${uniformLocation}.")
        uniforms[uniformName] = uniformLocation
    }

    fun setUniform(uniformName: String, value: Matrix4f) {
        val stack = MemoryStack.stackPush()
        stack.use {
            // 4x4 matrix
            val floatBuffer = it.mallocFloat(16)
            value.get(floatBuffer)
            glUniformMatrix4fv(uniforms[uniformName]!!, false, floatBuffer)
        }
    }

    fun bind() = glUseProgram(programId)
    fun unbind() = glUseProgram(0)

    fun cleanup() {
        unbind()
        if(programId != 0) glDeleteProgram(programId)
    }

}