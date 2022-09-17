package io.jyryuitpro.shoppi.android.review.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.jyryuitpro.shoppi.android.review.AssetLoader
import io.jyryuitpro.shoppi.android.review.network.ApiClient
import io.jyryuitpro.shoppi.android.review.repository.category.CategoryRemoteDataSource
import io.jyryuitpro.shoppi.android.review.repository.category.CategoryRepository
import io.jyryuitpro.shoppi.android.review.repository.home.HomeAssetDataSource
import io.jyryuitpro.shoppi.android.review.repository.home.HomeRepository
import io.jyryuitpro.shoppi.android.review.ui.category.CategoryViewModel
import io.jyryuitpro.shoppi.android.review.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                // TODO DI
                val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                // TODO DI
                val repository = CategoryRepository(CategoryRemoteDataSource(ApiClient.create()))
                CategoryViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}