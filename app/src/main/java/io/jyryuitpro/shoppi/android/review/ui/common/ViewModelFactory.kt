package io.jyryuitpro.shoppi.android.review.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.jyryuitpro.shoppi.android.review.AssetLoader
import io.jyryuitpro.shoppi.android.review.repository.HomeAssetDataSource
import io.jyryuitpro.shoppi.android.review.repository.HomeRepository
import io.jyryuitpro.shoppi.android.review.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            // TODO DI
            val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
            return HomeViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}