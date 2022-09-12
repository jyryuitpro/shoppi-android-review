package io.jyryuitpro.shoppi.android.review.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import io.jyryuitpro.shoppi.android.review.*

// https://developer.android.com/guide/fragments/lifecycle
class HomeFragment : Fragment() {

    // by viewModels() : 호출해서 HomeViewModel 생성
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // view: View : onCreateView에서 생성된 View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_home_title)
        val toolbarIcon = view.findViewById<ImageView>(R.id.toolbar_home_icon)
        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewpagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

        val assetLoader = AssetLoader()
        val homeData = assetLoader.getJsonString(requireContext(), "home.json")
        Log.d("homeData", homeData ?: "")

        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")

        if (!homeJsonString.isNullOrEmpty()) {
            val gson = Gson()
            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)

            // viewLifecycleOwner : lifecycle이 변경되는 것에 대한 알림을 받아 현재의 lifecycle 상태를 알고 있는 객체를 의미합니다.
            viewModel.title.observe(viewLifecycleOwner) { title ->
                toolbarTitle.text = title.text
                GlideApp.with(this)
                    .load(title.iconUrl)
                    .into(toolbarIcon)
            }

            viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
                viewpager.adapter = HomeBannerAdapter().apply {
                    submitList(banners)
                }
            }

            // 디바이스의 가로 길이 - 한 페이지의 가로 길이 - 페이지 간의 간격 = 다음 페이지가 이동해야하는 거리 값
            // dp to px
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)

            val screenWidth = resources.displayMetrics.widthPixels
            // val offset = screenWidth - 312dp - 16dp
            val offset = screenWidth - pageWidth - pageMargin

            viewpager.offscreenPageLimit = 3
            // PageTransformer
            viewpager.setPageTransformer { page, position ->
                Log.d("setPageTransformer", "$position")
                page.translationX = position * -offset
            }

            // TabConfigurationStrategy
            TabLayoutMediator(viewpagerIndicator, viewpager) { tab, position ->

            }.attach()
        }
    }
}