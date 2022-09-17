package io.jyryuitpro.shoppi.android.review.ui.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import io.jyryuitpro.shoppi.android.review.R
import io.jyryuitpro.shoppi.android.review.databinding.FragmentCategoryBinding
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

        val categoryAdapter = CategoryAdapter()
        binding.rvCategoryList.adapter = categoryAdapter

        viewModel.items.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }
    }
}