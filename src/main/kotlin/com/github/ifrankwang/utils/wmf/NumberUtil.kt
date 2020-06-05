package com.github.ifrankwang.utils.wmf

/**
 * @author Frank Wang
 */

fun Short.toUnsigned(): Short {
    return (this.toInt() and 0xffff).toShort()
}

fun Int.toUnsigned(): Int {
    return (this.toLong() and 0xffffffffL).toInt()
}

fun Long.toUnsigned(): Long {
    return (this and 0xffffffffL)
}
