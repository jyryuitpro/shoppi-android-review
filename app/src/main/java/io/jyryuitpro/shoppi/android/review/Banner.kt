package io.jyryuitpro.shoppi.android.review

data class Banner(
    val backgroundImageUrl: String,
    val badge: BannerBadge,
    val label: String,
    val productDetail: ProductDetail,
)

data class BannerBadge(
    val label: String,
    val backgroundColor: String
)

data class ProductDetail(
    val brandName: String,
    val label: String,
    val discountRate: Int,
    val price: Int,
    val thumbnailImageUrl: String,
    val productId: String,
)


