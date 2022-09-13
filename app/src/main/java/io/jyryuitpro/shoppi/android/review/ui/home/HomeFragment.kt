package io.jyryuitpro.shoppi.android.review.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.jyryuitpro.shoppi.android.review.*
import io.jyryuitpro.shoppi.android.review.databinding.FragmentHomeBinding
import io.jyryuitpro.shoppi.android.review.ui.common.ViewModelFactory

// https://developer.android.com/guide/fragments/lifecycle
class HomeFragment : Fragment() {

    // by viewModels() : 호출해서 HomeViewModel 생성
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // view: View : onCreateView에서 생성된 View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_home_title)
//        val toolbarIcon = view.findViewById<ImageView>(R.id.toolbar_home_icon)
//        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
//        val viewpagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

        // 데이터바인딩 설정 후 필수
        binding.lifecycleOwner = viewLifecycleOwner

        setToolbar()

        // TODO 수정 필요
        // 기존 코드의 문제점 : topBanners가 변경이 되어야지만 adapter를 초기화 하고 있음
//        viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
//            viewpager.adapter = HomeBannerAdapter().apply {
//                submitList(banners)
//            }
//        }

        // 수정된 코드
//        viewpager.adapter = HomeBannerAdapter().apply {
//            viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
//                submitList(banners)
//            }
//        }

        setTopBanners()
    }

    private fun setToolbar() {
        // viewLifecycleOwner : lifecycle이 변경되는 것에 대한 알림을 받아 현재의 lifecycle 상태를 알고 있는 객체를 의미합니다.
        viewModel.title.observe(viewLifecycleOwner) { title ->
            //            toolbarTitle.text = title.text
            //            GlideApp.with(this)
            //                .load(title.iconUrl)
            //                .into(toolbarIcon)
            binding.title = title
        }
    }

    private fun setTopBanners() {
        with(binding.viewpagerHomeBanner) {
            adapter = HomeBannerAdapter().apply {
                viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
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

            offscreenPageLimit = 3
            // PageTransformer
            setPageTransformer { page, position ->
                Log.d("setPageTransformer", "$position")
                page.translationX = position * -offset
            }

            // TabConfigurationStrategy
            TabLayoutMediator(binding.viewpagerHomeBannerIndicator, this) { tab, position ->

            }.attach()
        }
    }
}