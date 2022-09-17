package io.jyryuitpro.shoppi.android.review.repository.category

import io.jyryuitpro.shoppi.android.review.model.Category
import io.jyryuitpro.shoppi.android.review.network.ApiClient

class CategoryRemoteDataSource(private val apiClient: ApiClient) : CategoryDataSource {
    override suspend fun getCategories(): List<Category> {
        return apiClient.getCategories()
    }
}