package io.jyryuitpro.shoppi.android.review.repository.category

import io.jyryuitpro.shoppi.android.review.model.Category

interface CategoryDataSource {

    suspend fun getCategories(): List<Category>
}