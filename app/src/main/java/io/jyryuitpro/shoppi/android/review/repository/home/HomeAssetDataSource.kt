package io.jyryuitpro.shoppi.android.review.repository.home

import com.google.gson.Gson
import io.jyryuitpro.shoppi.android.review.AssetLoader
import io.jyryuitpro.shoppi.android.review.model.HomeData

class HomeAssetDataSource(private val assetLoader: AssetLoader) : HomeDataSource {

    private val gson = Gson()

    override fun getHomeData(): HomeData? {
        return assetLoader.getJsonString("home.json")?.let { homeJsonString ->
            gson.fromJson(homeJsonString, HomeData::class.java)
        }
    }
}