package io.jyryuitpro.shoppi.android.review.ui.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import io.jyryuitpro.shoppi.android.review.R
import io.jyryuitpro.shoppi.android.review.common.KEY_CATEGORY_ID
import io.jyryuitpro.shoppi.android.review.common.KEY_CATEGORY_LABEL
import io.jyryuitpro.shoppi.android.review.databinding.FragmentCategoryBinding
import io.jyryuitpro.shoppi.android.review.model.Category
import io.jyryuitpro.shoppi.android.review.ui.common.EventObserver
import io.jyryuitpro.shoppi.android.review.ui.common.ViewModelFactory

class CategoryFragment : Fragment() {

    private val viewModel: CategoryViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_category, container, false)
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter(viewModel)
        binding.rvCategoryList.adapter = categoryAdapter

        viewModel.items.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }

        viewModel.openCategoryEvent.observe(viewLifecycleOwner, EventObserver {
            openCategoryDetail(it.categoryId, it.label)
        })

//        viewModel.openCategoryEvent.observe(viewLifecycleOwner, object : Observer<Category> {
//            override fun onChanged(t: Category?) {
//
//            }
//        })

//        viewModel.openCategoryEvent.observe(viewLifecycleOwner) {
//            openCategoryDetail(it.categoryId, it.label)
//        }
    }

    private fun openCategoryDetail(categoryId: String, categoryLabel: String) {
        findNavController().navigate(R.id.action_category_to_category_detail, bundleOf(
            KEY_CATEGORY_ID to categoryId,
            KEY_CATEGORY_LABEL to categoryLabel
        ))
    }
}