package com.github.ifrankwang.utils.wmf

/**
 * @author Frank Wang
 */
interface StandardMeta {
    /* Total size of the record in WORDs */
    val size: Int
    /* Function number (defined in WINDOWS.H) */
    val function: String
    /* Parameter values passed to function */
    val parameters: List<String>
}
