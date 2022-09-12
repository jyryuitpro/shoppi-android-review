package io.jyryuitpro.shoppi.android.review.before.aac

import android.content.Context
import android.util.Log

// 실습 : JSON asset 불러오기
class AssetLoader {

    fun getJsonString(context: Context, fileName: String): String? {
//        val string = loadAsset(context, fileName)
        // runCatching : 성공, 실패로 나뉘어지는 작업을 처리할 때, 사용할 수 있음
        return kotlin.runCatching {
            loadAsset(context, fileName)
        }.getOrNull()
    }

    private fun loadAsset(context: Context, fileName: String): String {
        return context.assets.open(fileName).use { inputStream ->
            val size = inputStream.available()
            val bytes = ByteArray(size)
            inputStream.read(bytes)
//            val homeData = String(bytes)
//            Log.d("homeData", homeData)
            String(bytes)
        }
    }
}