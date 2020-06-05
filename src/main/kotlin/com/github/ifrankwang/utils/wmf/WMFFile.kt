package com.github.ifrankwang.utils.wmf

import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * @author Frank Wang
 */
class WMFFile private constructor(
    val placeableMetaHeader: PlaceableMetaHeader,
    val standardMetaHeader: StandardMetaHeader,
    val standardMetaRecords: List<StandardMeta>
) {

    companion object {
        fun fromFile(file: File): WMFFile? {
            return ByteBuffer.wrap(file.readBytes()).apply {
                order(ByteOrder.LITTLE_ENDIAN)
            }.let { fromBuffer(it) }
        }

        fun fromBuffer(buffer: ByteBuffer): WMFFile? {
            return try {
                /* get Placeable Metafile Header */
                val placeableMetaHeader = PlaceableMetaHeader.fromBuffer(buffer)

                /* get Standard Metafile Header */
                val standardMetaHeader = StandardMetaHeader.fromBuffer(buffer)

                /* get Standard Metafile Records */
                val standardMetaRecords = mutableListOf<StandardMeta>()
                while (buffer.hasRemaining()) {
                    try {
                        standardMetaRecords.add(StandardMetaRecord.fromBuffer(buffer))
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }

                WMFFile(
                    placeableMetaHeader,
                    standardMetaHeader,
                    standardMetaRecords
                )
            } catch (e: Throwable) {
                e.printStackTrace()
                null
            }
        }
    }
}
