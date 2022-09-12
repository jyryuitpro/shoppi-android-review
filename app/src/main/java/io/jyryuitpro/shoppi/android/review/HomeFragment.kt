package io.jyryuitpro.shoppi.android.review

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import org.json.JSONObject

// https://developer.android.com/guide/fragments/lifecycle
class HomeFragment : Fragment() {
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

//        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
//        button.setOnClickListener {
//            val transaction = parentFragmentManager.beginTransaction()
//            transaction.add(R.id.container_main, ProductDetailFragment())
//            transaction.commit()
//            findNavController().navigate(R.id.action_home_to_product_detail)
//        }

        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_home_title)
        val toolbarIcon = view.findViewById<ImageView>(R.id.toolbar_home_icon)
        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewpagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

        // 실습 : JSON asset 불러오기
        // class AssetLoader로 분리
//        context?.assets?.open("home.json")?.use { inputStream ->
//            val size = inputStream.available()
//            val bytes = ByteArray(size)
//            inputStream.read(bytes)
//            val homeData = String(bytes)
//            Log.d("homeData", homeData)
//        }

        val assetLoader = AssetLoader()
        val homeData = assetLoader.getJsonString(requireContext(), "home.json")
        Log.d("homeData", homeData ?: "")

        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")

        if (!homeJsonString.isNullOrEmpty()) {
            val gson = Gson()
            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)

//            val jsonObject = JSONObject(homeData)
//            val title = jsonObject.getJSONObject("title")
//            val text = title.getString("text")
//            val iconUrl = title.getString("icon_url")

            // Data Class를 이용할 경우
//            val titleValue = Title(text, iconUrl)
//            toolbarTitle.text = titleValue.text
//            GlideApp.with(this)
//                .load(titleValue.iconUrl)
//                .into(toolbarIcon)

            toolbarTitle.text = homeData.title.text
            GlideApp.with(this)
                .load(homeData.title.iconUrl)
                .into(toolbarIcon)

            // gson을 사용해야하는 이유
//            val topBanners = jsonObject.getJSONArray("top_banners")
//            val size = topBanners.length()
//            for (index in 0 until size) {
//                val bannerObject = topBanners.getJSONObject(index)
//                val backgroundImageUrl = bannerObject.getString("background_image_url")
//                val badgeObject = bannerObject.getJSONObject("badge")
//                val badgeLabel = badgeObject.getString("label")
//                val badgeBackgroundColor = badgeObject.getString("background_color")
//                val bannerBadge = BannerBadge(badgeLabel, badgeBackgroundColor)
//
//                val banner = Banner(
//                    backgroundImageUrl,
//                    bannerBadge,
//                    bannerLabel,
//                    bannerProductDetail
//                )
//            }

            viewpager.adapter = HomeBannerAdapter().apply {
                submitList(homeData.topBanners)
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

//            val topBanners = jsonObject.getJSONArray("top_banners")
//            val firstBanner = topBanners.getJSONObject(0)
//            val label = firstBanner.getString("label")
//            val productDetail = firstBanner.getJSONObject("product_detail")
//            val price = productDetail.getInt("price")

//            Log.d("title", "text=${text}, iconUrl=${iconUrl}")
//            Log.d("firstBanner", "label=${label}, price=${price}")
        }
    }
}