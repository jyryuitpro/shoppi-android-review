package io.jyryuitpro.shoppi.android.review.model

import com.google.gson.annotations.SerializedName
import io.jyryuitpro.shoppi.android.review.Banner

data class HomeData(
    val title: Title,
    @SerializedName("top_banners") val topBanners: List<Banner>
)
