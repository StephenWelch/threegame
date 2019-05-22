package com.threegame.engine

import com.threegame.util.Util
import de.matthiasmann.twl.utils.PNGDecoder
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL30.glGenerateMipmap
import java.nio.ByteBuffer

class Texture(filename: String) {

    private val textureBuffer: ByteBuffer
    val id: Int = glGenTextures()

    init {
        val textureDecoder = PNGDecoder(Util.loadResource(filename))
        // 4 bytes per pixel, # of pixels = width * height
        textureBuffer = ByteBuffer.allocateDirect(4 * textureDecoder.width * textureDecoder.height)
        textureDecoder.decode(textureBuffer, textureDecoder.width * 4, PNGDecoder.Format.RGBA)
        textureBuffer.flip()

        glBindTexture(GL_TEXTURE_2D, id)
        // One byte for each color component
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1)

        // Color format is RGBA, each pixel is represented by an unsigned byte
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, textureDecoder.width, textureDecoder.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, textureBuffer)
        glGenerateMipmap(GL_TEXTURE_2D)
    }

    fun cleanup() {
        glDeleteTextures(id)
    }

}