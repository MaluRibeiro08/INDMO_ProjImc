package com.example.primeiroapp.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
    return Base64.encodeToString(bitmapArray.toByteArray(), Base64.DEFAULT)
}

//fun convertBase64ToBitmap(base644: String) : Bitmap
//{
//    val decodedString: ByteArray = Base64.decode(encodedImage, Base64.DEFAULT)
//    val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

//    val base64 = base644.encodeToByteArray().decodeToString()
//    val decodedString: Array<Byte> = Base64.decode(base64, Base64.DEFAULT)
//    val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//
//
//    //criar um array de bytes a partir do bit map
//    val bitmapArray = ByteArrayOutputStream()
//
//    //comprimindo o bitmap para um array de bytes
//    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bitmapArray)
//    //comprimimos para PNG com a qualidade original (100) e colocamos o resultado da compressão no bitmapArray
//
//    //converte o array de bytes para string com ajuda do base64
//    return Base64.encodeToString(bitmapArray.toByteArray(), Base64.DEFAULT)
//}