package io.jyryuitpro.shoppi.android.review.repository.home

import io.jyryuitpro.shoppi.android.review.model.HomeData

class HomeRepository(private val assetDataSource: HomeAssetDataSource) {

    fun getHomeData(): HomeData? {
        return assetDataSource.getHomeData()
    }
}