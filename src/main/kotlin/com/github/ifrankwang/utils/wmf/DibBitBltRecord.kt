package com.github.ifrankwang.utils.wmf

import java.nio.ByteBuffer

/**
 * @author Frank Wang
 */
data class DibBitBltRecord(
    /* Total size of the record in WORDs */
    override val size: Int,
    /* High-order word for the raster operation */
    val rasterOperation: String,
    /* Y-coordinate of the source origin */
    val sourceOriginY: Short,
    /* X-coordinate of the source origin */
    val sourceOriginX: Short,
    /* Destination width */
    val destWidth: Short,
    /* Destination height */
    val destHeight: Short,
    /* Y-coordinate of the destination origin */
    val destOriginY: Short,
    /* X-coordinate of the destination origin */
    val destOriginX: Short,
    /* Width of bitmap in pixels */
    val width: Int,
    /* Height of bitmap in scan lines */
    val height: Int,
    /* Number of bytes in each scan line */
    val bytesPerLine: Int,
    /* Number of color planes in the bitmap */
    val colorPlanesNum: Short,
    /* Number of bits in each pixel */
    val bitsPerPixel: Short,
    /* Compression type */
    val compression: String,
    /* Size of bitmap in bytes */
    val bitmapByteSize: Int,
    /* Width of image in pixels per meter */
    val pixelsWidthPerMeter: Long,
    /* Height of image in pixels per meter */
    val pixelsHeightPerMeter: Long,
    /* Number of colors used */
    val colorNum: Int,
    /* Number of important colors */
    val importantColorNum: Int,
    /* Bitmap data */
    val bitmap: List<String>
): StandardMeta {

    /* Function number (defined in WINDOWS.H) */
    override val function: String = "DibBitblt"

    override val parameters: List<String> = listOf(
        rasterOperation, sourceOriginY.toString(), sourceOriginX.toString(), destWidth.toString(), destHeight.toString(),
        destOriginY.toString(), destOriginX.toString(), width.toString(), height.toString(),
        bytesPerLine.toString(), colorPlanesNum.toString(), bitsPerPixel.toString(), compression,
        bitmapByteSize.toString(), pixelsWidthPerMeter.toString(), pixelsHeightPerMeter.toString(), colorNum.toString(),
        importantColorNum.toString(), bitmap.toString()
    )

    companion object {
        fun fromBuffer(size: Int, buffer: ByteBuffer): DibBitBltRecord {
            return DibBitBltRecord(
                size = size,
                rasterOperation = buffer.getWord(),
                sourceOriginY = buffer.getUnsignedShort(),
                sourceOriginX = buffer.getUnsignedShort(),
                destWidth = buffer.getUnsignedShort(),
                destHeight = buffer.getUnsignedShort(),
                destOriginY = buffer.getUnsignedShort(),
                destOriginX = buffer.getUnsignedShort(),
                width = buffer.getUnsignedInt(),
                height = buffer.getUnsignedInt(),
                bytesPerLine = buffer.getUnsignedInt(),
                colorPlanesNum = buffer.getUnsignedShort(),
                bitsPerPixel = buffer.getUnsignedShort(),
                compression = buffer.getDWord(),
                bitmapByteSize = buffer.getUnsignedInt(),
                pixelsWidthPerMeter = buffer.getUnsignedLong(),
                pixelsHeightPerMeter = buffer.getUnsignedLong(),
                colorNum = buffer.getUnsignedInt(),
                importantColorNum = buffer.getUnsignedInt(),
                bitmap = buffer.toRGB()
            )
        }
    }
}
