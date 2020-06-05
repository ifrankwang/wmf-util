package com.github.ifrankwang.utils.wmf

import java.nio.ByteBuffer

/**
 * @author Frank Wang
 */
data class StandardMetaRecord(
    /* Total size of the record in WORDs */
    override val size: Int,
    /* Function number (defined in WINDOWS.H) */
    override val function: String,
    /* Parameter values passed to function */
    override val parameters: List<String>
): StandardMeta {

    companion object {
        private val functionMap = mapOf(
            0x0000 to "EOF",
            0x001E to "SaveDC",
            0x0035 to "RealizePalette",
            0x0037 to "SetPalEntries",
            0x004F to "StartPage",
            0x0050 to "EndPage",
            0x0052 to "AbortDoc",
            0x005E to "EndDoc",
            0x00F7 to "CreatePalette",
            0x00F8 to "CreateBrush",
            0x0102 to "SetBkMode",
            0x0103 to "SetMapMode",
            0x0104 to "SetROP2",
            0x0105 to "SetRelabs",
            0x0106 to "SetPolyFillMode",
            0x0107 to "SetStretchBltMode",
            0x0108 to "SetTextCharExtra",
            0x0127 to "RestoreDC",
            0x012A to "InvertRegion",
            0x012B to "PaintRegion",
            0x012C to "SelectClipRegion",
            0x012D to "SelectObject",
            0x012E to "SetTextAlign",
            0x0139 to "ResizePalette",
            0x0142 to "DibCreatePatternBrush",
            0x014C to "ResetDc",
            0x014D to "StartDoc",
            0x01F0 to "DeleteObject",
            0x01F9 to "CreatePatternBrush",
            0x01f0 to "DeleteObject",
            0x0201 to "SetBkColor",
            0x0209 to "SetTextColor",
            0x020A to "SetTextJustification",
            0x020B to "SetWindowOrg",
            0x020C to "SetWindowExt",
            0x020D to "SetViewportOrg",
            0x020E to "SetViewportExt",
            0x020F to "OffsetWindowOrg",
            0x0211 to "OffsetViewportOrg",
            0x0213 to "LineTo",
            0x0214 to "MoveTo",
            0x0220 to "OffsetClipRgn",
            0x0228 to "FillRegion",
            0x0231 to "SetMapperFlags",
            0x0234 to "SelectPalette",
            0x02FA to "CreatePenIndirect",
            0x02FB to "CreateFontIndirect",
            0x02FC to "CreateBrushIndirect",
            0x02FD to "CreateBitmapIndirect",
            0x0324 to "Polygon",
            0x0325 to "Polyline",
            0x0410 to "ScaleWindowExt",
            0x0412 to "ScaleViewportExt",
            0x0415 to "ExcludeClipRect",
            0x0416 to "IntersectClipRect",
            0x0418 to "Ellipse",
            0x0419 to "FloodFill",
            0x041B to "Rectangle",
            0x041F to "SetPixel",
            0x0429 to "FrameRegion",
            0x0436 to "AnimatePalette",
            0x0521 to "TextOut",
            0x0538 to "PolyPolygon",
            0x0548 to "ExtFloodFill",
            0x061C to "RoundRect",
            0x061D to "PatBlt",
            0x0626 to "Escape",
            0x062F to "DrawText",
            0x06FE to "CreateBitmap",
            0x06FF to "CreateRegion",
            0x0817 to "Arc",
            0x081A to "Pie",
            0x0830 to "Chord",
            0x0922 to "BitBlt",
            0x0940 to "DibBitblt",
            0x0A32 to "ExtTextOut",
            0x0B23 to "StretchBlt",
            0x0B41 to "DibStretchBlt",
            0x0F43 to "StretchDIBits",
            0x0d33 to "SetDibToDev"
        )

        fun fromBuffer(buffer: ByteBuffer): StandardMeta {
            val size = buffer.getUnsignedInt()
            val function = buffer.getUnsignedShort().toInt()

            return if (function == 0x0940) {
                DibBitBltRecord.fromBuffer(
                    size = size,
                    buffer = buffer
                )
            } else StandardMetaRecord(
                size = size,
                function = functionMap[function] ?: "Not Found",
                parameters = ((size - 3) downTo 1).map {
                    buffer.getWord()
                }
            )
        }
    }
}
