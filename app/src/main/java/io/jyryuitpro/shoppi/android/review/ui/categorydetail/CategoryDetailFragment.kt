package io.jyryuitpro.shoppi.android.review.ui.categorydetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import io.jyryuitpro.shoppi.android.review.R
import io.jyryuitpro.shoppi.android.review.common.KEY_CATEGORY_ID
import io.jyryuitpro.shoppi.android.review.common.KEY_CATEGORY_LABEL
import io.jyryuitpro.shoppi.android.review.databinding.FragmentCategoryDetailBinding

class CategoryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCategoryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_category_detail, container, false)
        binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

//        val categoryId = requireArguments().getString(KEY_CATEGORY_ID)
        setToolbar()
        setListAdapter()
    }

    private fun setToolbar() {
        val categoryLabel = requireArguments().getString(KEY_CATEGORY_LABEL)
        binding.toolbarCategoryDetail.title = categoryLabel
    }

    private fun setListAdapter() {
        val titleAdapter = CategorySectionTitleAdapter()
        val promotionAdapter = CategoryPromotionAdapter()
        binding.rvCategoryDetail.adapter = ConcatAdapter(titleAdapter, promotionAdapter)
    }
}