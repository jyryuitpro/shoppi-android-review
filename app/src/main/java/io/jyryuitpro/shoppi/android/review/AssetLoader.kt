package io.jyryuitpro.shoppi.android.review

import android.content.Context
import android.util.Log

// 실습 : JSON asset 불러오기
class AssetLoader(private val context: Context) {

    fun getJsonString(fileName: String): String? {
//        val string = loadAsset(context, fileName)
        // runCatching : 성공, 실패로 나뉘어지는 작업을 처리할 때, 사용할 수 있음
        return kotlin.runCatching {
            loadAsset(fileName)
        }.getOrNull()
    }

    private fun loadAsset(fileName: String): String {
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