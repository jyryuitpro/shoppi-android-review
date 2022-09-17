package io.jyryuitpro.shoppi.android.review.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.jyryuitpro.shoppi.android.review.Banner
import io.jyryuitpro.shoppi.android.review.model.Title
import io.jyryuitpro.shoppi.android.review.repository.home.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _title = MutableLiveData<Title>()
    val title: LiveData<Title> = _title

    private val _topBanners = MutableLiveData<List<Banner>>()
    val topBanners: LiveData<List<Banner>> = _topBanners

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        // TODO Data Layer - Repository에 요청
        val homeData = homeRepository.getHomeData()
        homeData?.let { homeData ->
            _title.value = homeData.title
            _topBanners.value = homeData.topBanners
        }
    }
}