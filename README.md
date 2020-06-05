# WMF Util

Util for reading Microsoft Windows Metafile (*.wmf)

## Reference

[Microsoft Windows Metafile (structure explanation)](http://wvware.sourceforge.net/caolan/ora-wmf.html)

## Usage

``` kotlin
// reading from file
val wmfFile = WMFFile.fromFile("path/to/your/wmf/file.wmf")
// reading from byte buffer
val wmfFile2 = WMFFile.fromBuffer(byteBuffer)
```
