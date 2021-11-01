package com.example.primeiroapp.util

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

fun convertBitmapToBase64(bitmap: Bitmap) : String
{
    //criar um array de bytes a partir do bit map
    val bitmapArray = ByteArrayOutputStream()

    //comprimindo o bitmap para um array de bytes
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bitmapArray)
        //comprimimos para PNG com a qualidade original (100) e colocamos o resultado da compressão no bitmapArray

    //converte o array de bytes para string com ajuda do base64
    return Base64.encodeToString(bitmapArray.toByteArray(),Base64.DEFAULT)


}