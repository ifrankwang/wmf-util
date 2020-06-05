package com.github.ifrankwang.utils.wmf

import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * @author Frank Wang
 */
fun ByteBuffer.getDWord(): String {
    return String.format("%08X", int.toUnsigned())
}

fun ByteBuffer.getWord(): String {
    return String.format("%04X", getUnsignedShort())
}

fun ByteBuffer.getUnsignedShort(): Short {
    return short.toUnsigned()
}

fun ByteBuffer.getUnsignedInt(): Int {
    return int.toUnsigned()
}

fun ByteBuffer.getUnsignedLong(): Long {
    return long.toUnsigned()
}

fun ByteBuffer.getByteString(): String {
    return String.format("%02X", get())
}

fun ByteBuffer.toRGB(): List<String> {
    val originEndion = order()
    val resultList = mutableListOf<String>()

    order(ByteOrder.BIG_ENDIAN)
    while (this.hasRemaining()) {
        try {
            resultList.add(getByteString() + getByteString() + getByteString() + getByteString())
        } catch (e: Throwable) {
            break
        }
    }

    order(originEndion)
    return resultList
}
