package com.kennycason.nn.data.image

import org.jblas.FloatMatrix
import java.awt.image.BufferedImage

class MatrixRGBImageEncoder : MatrixImageEncoder {

    override fun encode(image: Image): FloatMatrix {
        return encode(image.data())
    }

    override fun encode(bi: BufferedImage): FloatMatrix {
        val data = FloatMatrix(1, bi.width * 3 * bi.height)
        var i = 0
        for (col in 0 until bi.width ) {
            for (row in 0 until bi.height) {
                val rgb = bi.getRGB(col, row) and 0xFFFFFF

                val r = rgb and 0xFF0000 shr 16
                val g = rgb and 0xFF00 shr 8
                val b = rgb and 0xFF

                data.put(i, r / 255.0f)
                data.put(i + 1, g / 255.0f)
                data.put(i + 2, b / 255.0f)

                i += 3
            }
        }
        return data
    }


}