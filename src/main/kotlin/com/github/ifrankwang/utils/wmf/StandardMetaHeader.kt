package com.github.ifrankwang.utils.wmf

import java.nio.ByteBuffer

/**
 * @author Frank Wang
 */
data class StandardMetaHeader(
    /* Type of metafile (0=memory, 1=disk) */
    val fileType: Short,
    /* Version of Microsoft Windows used */
    val version: Short,
    /* Total size of the metafile in WORDs */
    val fileSize: Int,
    /* Number of objects in the file */
    val numOfObjects: Short,
    /* The size of largest record in WORDs */
    val maxRecordSize: Int
) {

    companion object {
        /* Size of header in WORDS (always 9) */
        private const val headerSize: Short = 9
        /* Not Used (always 0) */
        private const val numOfParams: Short = 0

        fun fromBuffer(buffer: ByteBuffer): StandardMetaHeader {
            val fileType = buffer.getUnsignedShort()
            val headerSize = buffer.getUnsignedShort()
            val version = buffer.getUnsignedShort()
            val fileSize = buffer.getUnsignedInt()
            val numOfObjects = buffer.getUnsignedShort()
            val maxRecordSize = buffer.getUnsignedInt()
            val numOfParams = buffer.getUnsignedShort()

            if (numOfParams != StandardMetaHeader.numOfParams
                || headerSize != StandardMetaHeader.headerSize) {
                throw IncorrectWmfFormatException()
            }

            return StandardMetaHeader(
                fileType = fileType,
                version = version,
                fileSize = fileSize,
                numOfObjects = numOfObjects,
                maxRecordSize = maxRecordSize
            )
        }
    }
}
