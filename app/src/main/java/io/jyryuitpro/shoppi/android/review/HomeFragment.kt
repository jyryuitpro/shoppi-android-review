package io.jyryuitpro.shoppi.android.review

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

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

        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
        button.setOnClickListener {
//            val transaction = parentFragmentManager.beginTransaction()
//            transaction.add(R.id.container_main, ProductDetailFragment())
//            transaction.commit()
            findNavController().navigate(R.id.action_home_to_product_detail)
        }

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
    }
}