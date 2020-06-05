package com.github.ifrankwang.utils.wmf

import java.nio.ByteBuffer

/**
 * @author Frank Wang
 */
data class PlaceableMetaHeader(
    /**
     * Left coordinate in metafile units
     */
    val left: Short,
    /**
     * Top coordinate in metafile units
     */
    val top: Short,
    /**
     * Right coordinate in metafile units
     */
    val right: Short,
    /**
     * Bottom coordinate in metafile units
     */
    val bottom: Short,
    /**
     * Number of metafile units per inch
     */
    val inch: Short,
    /**
     * Checksum value for previous 10 WORDs
     */
    val checksum: String
) {

    companion object {
        /**
         * Magic number (always 9AC6CDD7h)
         */
        private const val key: String = "9AC6CDD7"

        /**
         * Metafile HANDLE number (always 0)
         */
        private const val handle: String = "0000"

        /**
         * Reserved (always 0)
         */
        private const val reserved: String = "00000000"

        fun fromBuffer(buffer: ByteBuffer): PlaceableMetaHeader {
            val key = buffer.getDWord()
            val handle = buffer.getWord()
            val left = buffer.getUnsignedShort()
            val top = buffer.getUnsignedShort()
            val right = buffer.getUnsignedShort()
            val bottom = buffer.getUnsignedShort()
            val inch = buffer.getUnsignedShort()
            val reserved = buffer.getDWord()
            val checksum = buffer.getWord()

            if (
                key != PlaceableMetaHeader.key
                || handle != PlaceableMetaHeader.handle
                || reserved != PlaceableMetaHeader.reserved
            ) {
                throw IncorrectWmfFormatException()
            }

            return PlaceableMetaHeader(
                left,
                top,
                right,
                bottom,
                inch,
                checksum
            )
        }
    }
}
