package io.jyryuitpro.shoppi.android.review.repository.home

import io.jyryuitpro.shoppi.android.review.model.HomeData

interface HomeDataSource {

    fun getHomeData(): HomeData?
}